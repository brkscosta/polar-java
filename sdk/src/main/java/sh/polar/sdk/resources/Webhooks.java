package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.webhook.*;

import java.util.*;

public class Webhooks {
    private final PolarHttpClient http;

    public Webhooks(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarWebhookEndpointResponse> listEndpoints(int page, int limit) {
        return http.get("/webhooks/endpoints?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarWebhookEndpointResponse createEndpoint(Map<String, Object> body) {
        return http.post("/webhooks/endpoints", body, PolarWebhookEndpointResponse.class);
    }

    public PolarWebhookEndpointResponse getEndpoint(UUID id) {
        return http.get("/webhooks/endpoints/" + id, PolarWebhookEndpointResponse.class);
    }

    public PolarWebhookEndpointResponse updateEndpoint(UUID id, Map<String, Object> body) {
        return http.patch("/webhooks/endpoints/" + id, body, PolarWebhookEndpointResponse.class);
    }

    public void deleteEndpoint(UUID id) {
        http.delete("/webhooks/endpoints/" + id);
    }

    public PolarListResponse<PolarWebhookDeliveryResponse> listDeliveries(int page, int limit) {
        return http.get("/webhooks/deliveries?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public void redeliverEvent(UUID eventId) {
        http.post("/webhooks/events/" + eventId + "/redeliver");
    }
}
