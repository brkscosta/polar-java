package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.payment.PolarDisputeResponse;

import java.util.*;

public class Disputes {
    private final PolarHttpClient http;

    public Disputes(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarDisputeResponse> list(int page, int limit) {
        return http.get("/disputes/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarDisputeResponse get(UUID id) {
        return http.get("/disputes/" + id, PolarDisputeResponse.class);
    }
}
