package sh.polar.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Spring Boot configuration properties for the Polar SDK.
 * <p>
 * Configure in {@code application.properties} or {@code application.yml}:
 * <pre>
 * polar.access-token=your-access-token
 * polar.api-url=https://api.polar.sh/v1
 * polar.webhook-secret=your-webhook-secret
 * polar.connect-timeout-seconds=10
 * polar.max-retry-attempts=3
 * polar.retry-backoff-millis=1000
 * </pre>
 */
@ConfigurationProperties(prefix = "polar")
public record PolarProperties(
        String accessToken,
        String apiUrl,
        String webhookSecret,
        Integer connectTimeoutSeconds,
        Integer maxRetryAttempts,
        Long retryBackoffMillis
) {
    public String apiUrlOrDefault() {
        return apiUrl != null && !apiUrl.isBlank() ? apiUrl : "https://api.polar.sh/v1";
    }

    public int connectTimeoutOrDefault() {
        return connectTimeoutSeconds != null ? connectTimeoutSeconds : 10;
    }

    public int maxRetryAttemptsOrDefault() {
        return maxRetryAttempts != null ? maxRetryAttempts : 3;
    }

    public long retryBackoffMillisOrDefault() {
        return retryBackoffMillis != null ? retryBackoffMillis : 1000;
    }
}
