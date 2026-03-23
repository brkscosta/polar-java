package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.payment.PolarRefundResponse;

import java.util.*;

public class Refunds {
    private final PolarHttpClient http;

    public Refunds(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarRefundResponse> list(int page, int limit) {
        return http.get("/refunds/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarRefundResponse create(Map<String, Object> body) {
        return http.post("/refunds/", body, PolarRefundResponse.class);
    }
}
