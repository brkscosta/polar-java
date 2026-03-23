package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.meter.*;

import java.util.*;

public class Meters {
    private final PolarHttpClient http;

    public Meters(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarMeterResponse> list(int page, int limit) {
        return http.get("/meters/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarMeterResponse create(Map<String, Object> body) {
        return http.post("/meters/", body, PolarMeterResponse.class);
    }

    public PolarMeterResponse get(UUID id) {
        return http.get("/meters/" + id, PolarMeterResponse.class);
    }

    public PolarMeterResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/meters/" + id, body, PolarMeterResponse.class);
    }

    public Map<String, Object> getQuantities(UUID id) {
        return http.get("/meters/" + id + "/quantities",
                new TypeReference<Map<String, Object>>() {});
    }
}
