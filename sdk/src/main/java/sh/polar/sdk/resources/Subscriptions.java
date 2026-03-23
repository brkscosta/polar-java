package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.subscription.*;

import java.util.*;

public class Subscriptions {
    private final PolarHttpClient http;

    public Subscriptions(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarSubscriptionResponse> list(UUID customerId, boolean active, int limit) {
        return http.get("/subscriptions/?customer_id=" + customerId + "&active=" + active + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarSubscriptionResponse get(UUID id) {
        return http.get("/subscriptions/" + id, PolarSubscriptionResponse.class);
    }

    public PolarSubscriptionResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/subscriptions/" + id, body, PolarSubscriptionResponse.class);
    }

    public void revoke(UUID id) {
        http.delete("/subscriptions/" + id);
    }
}
