package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.organization.*;

import java.util.*;

public class Organizations {
    private final PolarHttpClient http;

    public Organizations(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarOrganizationResponse> list(int page, int limit) {
        return http.get("/organizations/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarOrganizationResponse create(Map<String, Object> body) {
        return http.post("/organizations/", body, PolarOrganizationResponse.class);
    }

    public PolarOrganizationResponse get(UUID id) {
        return http.get("/organizations/" + id, PolarOrganizationResponse.class);
    }

    public PolarOrganizationResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/organizations/" + id, body, PolarOrganizationResponse.class);
    }
}
