package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.license.*;

import java.util.*;

public class LicenseKeys {
    private final PolarHttpClient http;

    public LicenseKeys(PolarHttpClient http) { this.http = http; }

    public PolarListResponse<PolarLicenseKeyResponse> list(int page, int limit) {
        return http.get("/license-keys/?page=" + page + "&limit=" + limit,
                new TypeReference<>() {});
    }

    public PolarLicenseKeyResponse get(UUID id) {
        return http.get("/license-keys/" + id, PolarLicenseKeyResponse.class);
    }

    public PolarLicenseKeyResponse update(UUID id, Map<String, Object> body) {
        return http.patch("/license-keys/" + id, body, PolarLicenseKeyResponse.class);
    }

    public PolarLicenseKeyActivationResponse activate(Map<String, Object> body) {
        return http.post("/license-keys/activate", body, PolarLicenseKeyActivationResponse.class);
    }

    public void deactivate(Map<String, Object> body) {
        http.post("/license-keys/deactivate", body);
    }

    public PolarLicenseKeyValidationResponse validate(Map<String, Object> body) {
        return http.post("/license-keys/validate", body, PolarLicenseKeyValidationResponse.class);
    }
}
