package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.benefit.*;

import java.util.*;

public class Benefits {
    private final PolarHttpClient http;

    public Benefits(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarBenefitResponse> list(int page, int limit) {
        return http.get("/benefits/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarBenefitResponse create(Map<String, Object> body) {
        return http.post("/benefits/", body, PolarBenefitResponse.class);
    }

    public PolarBenefitResponse get(UUID id) {
        return http.get("/benefits/" + id, PolarBenefitResponse.class);
    }

    public PolarBenefitResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/benefits/" + id, body, PolarBenefitResponse.class);
    }

    public void delete(UUID id) {
        http.delete("/benefits/" + id);
    }

    public PolarListResponse<PolarBenefitGrantResponse> listGrants(int page, int limit) {
        return http.get("/benefit-grants/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarListResponse<PolarBenefitGrantResponse> listGrantsByBenefit(UUID benefitId, int page, int limit) {
        return http.get("/benefits/" + benefitId + "/grants?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }
}
