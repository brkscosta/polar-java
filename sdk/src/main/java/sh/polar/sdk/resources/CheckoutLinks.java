package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.checkout.*;

import java.util.*;

public class CheckoutLinks {
    private final PolarHttpClient http;

    public CheckoutLinks(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarCheckoutLinkResponse> list(int page, int limit) {
        return http.get("/checkout-links/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarCheckoutLinkResponse create(Map<String, Object> body) {
        return http.post("/checkout-links/", body, PolarCheckoutLinkResponse.class);
    }

    public PolarCheckoutLinkResponse get(UUID id) {
        return http.get("/checkout-links/" + id, PolarCheckoutLinkResponse.class);
    }

    public PolarCheckoutLinkResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/checkout-links/" + id, body, PolarCheckoutLinkResponse.class);
    }

    public void delete(UUID id) {
        http.delete("/checkout-links/" + id);
    }
}
