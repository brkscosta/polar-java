package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.customfield.*;

import java.util.*;

public class CustomFields {
    private final PolarHttpClient http;

    public CustomFields(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarCustomFieldResponse> list(int page, int limit) {
        return http.get("/custom-fields/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarCustomFieldResponse create(Map<String, Object> body) {
        return http.post("/custom-fields/", body, PolarCustomFieldResponse.class);
    }

    public PolarCustomFieldResponse get(UUID id) {
        return http.get("/custom-fields/" + id, PolarCustomFieldResponse.class);
    }

    public PolarCustomFieldResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/custom-fields/" + id, body, PolarCustomFieldResponse.class);
    }

    public void delete(UUID id) {
        http.delete("/custom-fields/" + id);
    }
}
