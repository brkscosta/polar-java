package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.meter.*;

import java.util.*;

public class CustomerMeters {
    private final PolarHttpClient http;

    public CustomerMeters(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarCustomerMeterResponse> list(int page, int limit) {
        return http.get("/customer-meters/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarCustomerMeterResponse get(UUID id) {
        return http.get("/customer-meters/" + id, PolarCustomerMeterResponse.class);
    }
}
