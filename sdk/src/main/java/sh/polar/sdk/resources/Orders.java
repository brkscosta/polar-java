package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.order.*;

import java.util.*;

public class Orders {
    private final PolarHttpClient http;

    public Orders(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarOrderResponse> list(UUID customerId, int limit) {
        return http.get("/orders/?customer_id=" + customerId + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarOrderResponse get(UUID id) {
        return http.get("/orders/" + id, PolarOrderResponse.class);
    }

    public PolarOrderResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/orders/" + id, body, PolarOrderResponse.class);
    }

    public PolarInvoiceResponse getInvoice(UUID id) {
        return http.get("/orders/" + id + "/invoice", PolarInvoiceResponse.class);
    }

    public PolarInvoiceResponse generateInvoice(UUID id) {
        return http.post("/orders/" + id + "/invoice", null, PolarInvoiceResponse.class);
    }
}
