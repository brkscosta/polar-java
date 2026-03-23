package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.customer.*;

import java.util.*;

public class Customers {
    private final PolarHttpClient http;

    public Customers(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarCustomerResponse> list(int page, int limit) {
        return http.get("/customers/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarCustomerResponse get(UUID id) {
        return http.get("/customers/" + id, PolarCustomerResponse.class);
    }

    public PolarCustomerResponse create(Map<String, Object> body) {
        return http.post("/customers/", body, PolarCustomerResponse.class);
    }

    public PolarCustomerResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/customers/" + id, body, PolarCustomerResponse.class);
    }

    public void delete(UUID id) {
        http.delete("/customers/" + id);
    }

    public PolarCustomerStateResponse getState(UUID id) {
        return http.get("/customers/" + id + "/state", PolarCustomerStateResponse.class);
    }

    public PolarCustomerResponse getByExternalId(String externalId) {
        return http.get("/customers/external/" + externalId, PolarCustomerResponse.class);
    }

    public PolarCustomerResponse updateByExternalId(String externalId, Map<String, Object> body) {
        return http.patch("/customers/external/" + externalId, body, PolarCustomerResponse.class);
    }

    public void deleteByExternalId(String externalId) {
        http.delete("/customers/external/" + externalId);
    }

    public PolarCustomerStateResponse getStateByExternalId(String externalId) {
        return http.get("/customers/external/" + externalId + "/state", PolarCustomerStateResponse.class);
    }

    public Optional<PolarCustomerResponse> findByEmail(String email) {
        try {
            PolarListResponse<PolarCustomerResponse> result = http.get(
                    "/customers/?email=" + email + "&limit=1",
                    new TypeReference<>() {});
            if (result.items() != null && !result.items().isEmpty()) {
                return Optional.of(result.items().get(0));
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public PolarCustomerSessionResponse createSession(UUID customerId) {
        return http.post("/customer-sessions/",
                Map.of("customer_id", customerId.toString()),
                PolarCustomerSessionResponse.class);
    }
}
