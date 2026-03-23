package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.discount.*;

import java.util.*;

public class Discounts {
    private final PolarHttpClient http;

    public Discounts(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarDiscountResponse> list(int page, int limit) {
        return http.get("/discounts/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarDiscountResponse create(Map<String, Object> body) {
        return http.post("/discounts/", body, PolarDiscountResponse.class);
    }

    public PolarDiscountResponse get(UUID id) {
        return http.get("/discounts/" + id, PolarDiscountResponse.class);
    }

    public PolarDiscountResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/discounts/" + id, body, PolarDiscountResponse.class);
    }

    public void delete(UUID id) {
        http.delete("/discounts/" + id);
    }
}
