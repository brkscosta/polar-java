package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.product.*;

import java.util.*;

public class Products {
    private final PolarHttpClient http;

    public Products(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarProductResponse> list(int limit) {
        return http.get("/products/?limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarProductResponse get(UUID id) {
        return http.get("/products/" + id, PolarProductResponse.class);
    }

    public PolarProductResponse create(Map<String, Object> body) {
        return http.post("/products/", body, PolarProductResponse.class);
    }

    public PolarProductResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/products/" + id, body, PolarProductResponse.class);
    }

    public PolarProductResponse updateBenefits(UUID id, Map<String, Object> body) {
        return http.post("/products/" + id + "/benefits", body, PolarProductResponse.class);
    }
}
