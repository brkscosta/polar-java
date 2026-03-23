package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.file.*;

import java.util.*;

public class Files {
    private final PolarHttpClient http;

    public Files(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarFileResponse> list(int page, int limit) {
        return http.get("/files/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarFileResponse create(Map<String, Object> body) {
        return http.post("/files/", body, PolarFileResponse.class);
    }

    public PolarFileResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/files/" + id, body, PolarFileResponse.class);
    }

    public void delete(UUID id) {
        http.delete("/files/" + id);
    }

    public PolarFileResponse confirmUpload(UUID id) {
        return http.post("/files/" + id + "/uploaded", null, PolarFileResponse.class);
    }
}
