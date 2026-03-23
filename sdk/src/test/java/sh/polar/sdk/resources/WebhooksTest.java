package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.webhook.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class WebhooksTest {

    private PolarHttpClient http;
    private Webhooks webhooks;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        webhooks = new Webhooks(http);
    }

    @Test
    void listEndpointsDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/webhooks/endpoints?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(webhooks.listEndpoints(1, 10)).isSameAs(expected);
    }

    @Test
    void createEndpointDelegatesToHttp() {
        var expected = mock(PolarWebhookEndpointResponse.class);
        var body = Map.<String, Object>of("url", "https://hook.example.com", "events", List.of("order.created"));
        when(http.post("/webhooks/endpoints", body, PolarWebhookEndpointResponse.class)).thenReturn(expected);
        assertThat(webhooks.createEndpoint(body)).isSameAs(expected);
    }

    @Test
    void getEndpointDelegatesToHttp() {
        var expected = mock(PolarWebhookEndpointResponse.class);
        when(http.get("/webhooks/endpoints/" + testId, PolarWebhookEndpointResponse.class)).thenReturn(expected);
        assertThat(webhooks.getEndpoint(testId)).isSameAs(expected);
    }

    @Test
    void updateEndpointDelegatesToHttp() {
        var expected = mock(PolarWebhookEndpointResponse.class);
        var body = Map.<String, Object>of("url", "https://new-hook.example.com");
        when(http.patch("/webhooks/endpoints/" + testId, body, PolarWebhookEndpointResponse.class)).thenReturn(expected);
        assertThat(webhooks.updateEndpoint(testId, body)).isSameAs(expected);
    }

    @Test
    void deleteEndpointDelegatesToHttp() {
        webhooks.deleteEndpoint(testId);
        verify(http).delete("/webhooks/endpoints/" + testId);
    }

    @Test
    void listDeliveriesDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/webhooks/deliveries?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(webhooks.listDeliveries(1, 10)).isSameAs(expected);
    }

    @Test
    void redeliverEventDelegatesToHttp() {
        webhooks.redeliverEvent(testId);
        verify(http).post("/webhooks/events/" + testId + "/redeliver");
    }
}
