package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.payment.*;

import java.util.*;

public class Payments {
    private final PolarHttpClient http;

    public Payments(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarPaymentResponse> list(int page, int limit) {
        return http.get("/payments/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarPaymentResponse get(UUID id) {
        return http.get("/payments/" + id, PolarPaymentResponse.class);
    }
}
