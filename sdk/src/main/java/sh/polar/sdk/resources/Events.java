package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.event.*;

import java.util.*;

public class Events {
    private final PolarHttpClient http;

    public Events(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarEventResponse> list(int page, int limit) {
        return http.get("/events/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarEventResponse get(UUID id) {
        return http.get("/events/" + id, PolarEventResponse.class);
    }

    public void ingest(Map<String, Object> body) {
        http.post("/events/ingest", body);
    }

    public List<String> listNames() {
        return http.get("/events/names",
                new TypeReference<List<String>>() {});
    }
}
