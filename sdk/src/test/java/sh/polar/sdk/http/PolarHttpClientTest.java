package sh.polar.sdk.http;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.errors.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
class PolarHttpClientTest {

    private HttpClient mockHttpClient;
    private HttpResponse<String> mockResponse;
    private PolarHttpClient client;

    @BeforeEach
    void setUp() throws Exception {
        mockHttpClient = mock(HttpClient.class);
        mockResponse = mock(HttpResponse.class);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);
        when(mockResponse.headers()).thenReturn(HttpHeaders.of(Map.of(), (a, b) -> true));
    }

    private PolarHttpClient buildClient() throws Exception {
        var realClient = PolarHttpClient.builder("test-token")
                .baseUrl("https://api.polar.sh/v1")
                .maxRetryAttempts(1)
                .build();

        var field = PolarHttpClient.class.getDeclaredField("httpClient");
        field.setAccessible(true);
        field.set(realClient, mockHttpClient);
        return realClient;
    }

    // ── Builder tests ─────────────────────────────

    @Test
    void builderRequiresToken() {
        assertThatThrownBy(() -> PolarHttpClient.builder(null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> PolarHttpClient.builder(""))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> PolarHttpClient.builder("  "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void builderCreatesClient() {
        PolarHttpClient c = PolarHttpClient.builder("token123")
                .baseUrl("https://sandbox-api.polar.sh/v1")
                .connectTimeout(15)
                .maxRetryAttempts(5)
                .retryBackoff(2000)
                .build();
        assertThat(c).isNotNull();
        assertThat(c.objectMapper()).isNotNull();
    }

    // ── GET tests ─────────────────────────────────

    @Test
    void getWithTypeReferenceDeserializes() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("{\"key\":\"value\"}");

        Map<String, String> result = client.get("/test", new TypeReference<>() {});
        assertThat(result).containsEntry("key", "value");
    }

    @Test
    void getWithClassDeserializes() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("{\"key\":\"value\"}");

        Map result = client.get("/test", Map.class);
        assertThat(result).containsEntry("key", "value");
    }

    // ── POST tests ────────────────────────────────

    @Test
    void postWithBodyAndTypeReference() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(201);
        when(mockResponse.body()).thenReturn("{\"id\":\"abc\"}");

        Map<String, String> result = client.post("/test", Map.of("name", "test"),
                new TypeReference<>() {});
        assertThat(result).containsEntry("id", "abc");
    }

    @Test
    void postWithBodyAndClass() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(201);
        when(mockResponse.body()).thenReturn("{\"id\":\"abc\"}");

        Map result = client.post("/test", Map.of("name", "test"), Map.class);
        assertThat(result).containsEntry("id", "abc");
    }

    @Test
    void postVoidWithBody() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(204);
        when(mockResponse.body()).thenReturn("");

        assertThatCode(() -> client.post("/test", Map.of("key", "val")))
                .doesNotThrowAnyException();
    }

    @Test
    void postVoidNoBody() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(204);
        when(mockResponse.body()).thenReturn("");

        assertThatCode(() -> client.post("/test"))
                .doesNotThrowAnyException();
    }

    // ── PATCH tests ───────────────────────────────

    @Test
    void patchDeserializesResponse() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("{\"updated\":true}");

        Map result = client.patch("/test/123", Map.of("name", "new"), Map.class);
        assertThat(result).containsEntry("updated", true);
    }

    // ── DELETE tests ──────────────────────────────

    @Test
    void deleteCompletesSuccessfully() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(204);
        when(mockResponse.body()).thenReturn("");

        assertThatCode(() -> client.delete("/test/123"))
                .doesNotThrowAnyException();
    }

    // ── Error handling tests ──────────────────────

    @Test
    void throws401AuthenticationException() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(401);
        when(mockResponse.body()).thenReturn("Unauthorized");

        assertThatThrownBy(() -> client.get("/test", Map.class))
                .isInstanceOf(PolarAuthenticationException.class);
    }

    @Test
    void throws404NotFoundException() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(404);
        when(mockResponse.body()).thenReturn("Not found");

        assertThatThrownBy(() -> client.get("/test/missing", Map.class))
                .isInstanceOf(PolarNotFoundException.class);
    }

    @Test
    void throws422ValidationException() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(422);
        when(mockResponse.body()).thenReturn("{\"detail\":[{\"msg\":\"invalid\"}]}");

        assertThatThrownBy(() -> client.post("/test", Map.of(), Map.class))
                .isInstanceOf(PolarValidationException.class);
    }

    @Test
    void throws429RateLimitException() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(429);
        when(mockResponse.body()).thenReturn("rate limited");

        assertThatThrownBy(() -> client.get("/test", Map.class))
                .isInstanceOf(PolarRateLimitException.class);
    }

    @Test
    void throws500ServerError() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(500);
        when(mockResponse.body()).thenReturn("Internal error");

        assertThatThrownBy(() -> client.get("/test", Map.class))
                .isInstanceOf(PolarApiException.class)
                .hasMessageContaining("server error");
    }

    @Test
    void throwsOnUnknown4xxError() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(403);
        when(mockResponse.body()).thenReturn("Forbidden");

        assertThatThrownBy(() -> client.get("/test", Map.class))
                .isInstanceOf(PolarApiException.class)
                .hasMessageContaining("403");
    }

    @Test
    void throwsOnIOException() throws Exception {
        client = buildClient();
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(new IOException("Connection refused"));

        assertThatThrownBy(() -> client.get("/test", Map.class))
                .isInstanceOf(PolarApiException.class)
                .hasMessageContaining("failed after");
    }

    @Test
    void throwsOnParseError() throws Exception {
        client = buildClient();
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("not json {{{");

        assertThatThrownBy(() -> client.get("/test", Map.class))
                .isInstanceOf(PolarApiException.class)
                .hasMessageContaining("parse");
    }

    // ── ObjectMapper test ─────────────────────────

    @Test
    void objectMapperIsExposed() {
        PolarHttpClient c = PolarHttpClient.builder("token").build();
        assertThat(c.objectMapper()).isNotNull();
    }
}
