package sh.polar.sdk.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.polar.sdk.errors.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Framework-agnostic HTTP transport for the Polar API.
 * <p>
 * Uses {@link java.net.http.HttpClient} (Java 11+) — zero external dependencies.
 * Handles JSON serialization, error mapping, and retry with exponential backoff.
 */
public class PolarHttpClient {

    private static final Logger log = LoggerFactory.getLogger(PolarHttpClient.class);

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;
    private final String accessToken;
    private final int maxAttempts;
    private final long backoffMillis;

    private PolarHttpClient(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.accessToken = builder.accessToken;
        this.maxAttempts = builder.maxAttempts;
        this.backoffMillis = builder.backoffMillis;
        this.objectMapper = createObjectMapper();
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(builder.connectTimeoutSeconds))
                .build();
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    // ── Public API ────────────────────────────────

    public <T> T get(String path, Class<T> responseType) {
        return execute("GET", path, null, responseType);
    }

    public <T> T get(String path, TypeReference<T> responseType) {
        return execute("GET", path, null, responseType);
    }

    public <T> T post(String path, Object body, Class<T> responseType) {
        return execute("POST", path, body, responseType);
    }

    public <T> T post(String path, Object body, TypeReference<T> responseType) {
        return execute("POST", path, body, responseType);
    }

    public void post(String path, Object body) {
        executeVoid("POST", path, body);
    }

    public void post(String path) {
        executeVoid("POST", path, null);
    }

    public <T> T patch(String path, Object body, Class<T> responseType) {
        return execute("PATCH", path, body, responseType);
    }

    public void delete(String path) {
        executeVoid("DELETE", path, null);
    }

    public ObjectMapper objectMapper() {
        return objectMapper;
    }

    // ── Internal execution ────────────────────────

    private <T> T execute(String method, String path, Object body, Class<T> responseType) {
        String responseBody = executeWithRetry(method, path, body);
        try {
            return objectMapper.readValue(responseBody, responseType);
        } catch (IOException e) {
            throw new PolarApiException("Failed to parse Polar API response", e);
        }
    }

    private <T> T execute(String method, String path, Object body, TypeReference<T> responseType) {
        String responseBody = executeWithRetry(method, path, body);
        try {
            return objectMapper.readValue(responseBody, responseType);
        } catch (IOException e) {
            throw new PolarApiException("Failed to parse Polar API response", e);
        }
    }

    private void executeVoid(String method, String path, Object body) {
        executeWithRetry(method, path, body);
    }

    private String executeWithRetry(String method, String path, Object body) {
        IOException lastException = null;
        String url = baseUrl + path;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                HttpRequest.Builder reqBuilder = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .timeout(Duration.ofSeconds(30));

                if (body != null) {
                    String json = objectMapper.writeValueAsString(body);
                    reqBuilder.method(method, HttpRequest.BodyPublishers.ofString(json));
                } else if ("POST".equals(method)) {
                    reqBuilder.method(method, HttpRequest.BodyPublishers.noBody());
                } else if ("PATCH".equals(method)) {
                    reqBuilder.method(method, HttpRequest.BodyPublishers.noBody());
                } else if ("DELETE".equals(method)) {
                    reqBuilder.DELETE();
                } else {
                    reqBuilder.GET();
                }

                long start = System.currentTimeMillis();
                HttpResponse<String> response = httpClient.send(reqBuilder.build(),
                        HttpResponse.BodyHandlers.ofString());
                long elapsed = System.currentTimeMillis() - start;

                int statusCode = response.statusCode();
                log.debug("Polar API {} {} → {} ({}ms)", method, path, statusCode, elapsed);

                boolean isRetryable = statusCode == 429 || statusCode >= 500;
                if (isRetryable && attempt < maxAttempts) {
                    long delay = calculateDelay(attempt, response);
                    log.warn("Polar API returned {} for {} {} — retrying in {}ms (attempt {}/{})",
                            statusCode, method, path, delay, attempt, maxAttempts);
                    sleepOrAbort(delay);
                    continue;
                }

                handleErrorStatus(statusCode, response.body(), url);
                return response.body();

            } catch (IOException e) {
                lastException = e;
                if (attempt < maxAttempts) {
                    long delay = backoffMillis * (1L << (attempt - 1));
                    log.warn("Polar API request failed for {} {} — retrying in {}ms (attempt {}/{}): {}",
                            method, path, delay, attempt, maxAttempts, e.getMessage());
                    sleepOrAbort(delay);
                } else {
                    throw new PolarApiException("Polar API request failed after " + maxAttempts + " attempts", e);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new PolarApiException("Polar API request interrupted", e);
            }
        }

        throw new PolarApiException("Polar API request failed after " + maxAttempts + " attempts", lastException);
    }

    private void handleErrorStatus(int statusCode, String body, String url) {
        if (statusCode >= 200 && statusCode < 300) return;

        switch (statusCode) {
            case 401 -> throw new PolarAuthenticationException("Polar API authentication failed");
            case 404 -> throw new PolarNotFoundException("Polar API resource not found: " + url);
            case 422 -> throw new PolarValidationException("Polar API validation error: " + body);
            case 429 -> throw new PolarRateLimitException(
                    "Polar API rate limit exceeded. Production: 500/min, Sandbox: 100/min");
            default -> {
                if (statusCode >= 500) {
                    throw new PolarApiException("Polar API server error: " + statusCode, statusCode);
                }
                throw new PolarApiException("Polar API error: " + statusCode + " — " + body, statusCode);
            }
        }
    }

    private long calculateDelay(int attempt, HttpResponse<String> response) {
        String retryAfter = response.headers().firstValue("Retry-After").orElse(null);
        if (retryAfter != null) {
            try {
                return Long.parseLong(retryAfter) * 1000;
            } catch (NumberFormatException ignored) { }
        }
        return backoffMillis * (1L << (attempt - 1));
    }

    private void sleepOrAbort(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new PolarApiException("Polar API retry interrupted", e);
        }
    }

    // ── Builder ───────────────────────────────────

    public static Builder builder(String accessToken) {
        return new Builder(accessToken);
    }

    public static class Builder {
        private String baseUrl = "https://api.polar.sh/v1";
        private final String accessToken;
        private int connectTimeoutSeconds = 10;
        private int maxAttempts = 3;
        private long backoffMillis = 1000;

        private Builder(String accessToken) {
            if (accessToken == null || accessToken.isBlank()) {
                throw new IllegalArgumentException("Access token must not be blank");
            }
            this.accessToken = accessToken;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder connectTimeout(int seconds) {
            this.connectTimeoutSeconds = seconds;
            return this;
        }

        public Builder maxRetryAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
            return this;
        }

        public Builder retryBackoff(long millis) {
            this.backoffMillis = millis;
            return this;
        }

        public PolarHttpClient build() {
            return new PolarHttpClient(this);
        }
    }
}
