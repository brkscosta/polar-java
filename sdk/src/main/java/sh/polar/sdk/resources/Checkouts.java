package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.checkout.*;

import java.util.*;

public class Checkouts {
    private final PolarHttpClient http;

    public Checkouts(PolarHttpClient http) { this.http = http; }

    public PolarCheckoutResponse create(Map<String, Object> body) {
        return http.post("/checkouts/", body, PolarCheckoutResponse.class);
    }

    public PolarCheckoutResponse create(String productId, String email, String successUrl, Map<String, String> metadata) {
        Map<String, Object> body = new HashMap<>();
        body.put("products", List.of(productId));
        body.put("success_url", successUrl);
        body.put("customer_email", email);
        if (metadata != null && !metadata.isEmpty()) {
            body.put("metadata", metadata);
        }
        return create(body);
    }

    public PolarListResponse<PolarCheckoutResponse> list(int page, int limit) {
        return http.get("/checkouts/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarCheckoutResponse get(UUID id) {
        return http.get("/checkouts/" + id, PolarCheckoutResponse.class);
    }

    public PolarCheckoutResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/checkouts/" + id, body, PolarCheckoutResponse.class);
    }
}
