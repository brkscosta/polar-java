package sh.polar.sdk.models.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Represents a license key validation response from Polar API.
 * Used for POST /license-keys/validate endpoint response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarLicenseKeyValidationResponse(
        @JsonProperty("valid") Boolean valid,
        @JsonProperty("license_key") PolarLicenseKeyResponse licenseKey,
        @JsonProperty("activation") Map<String, Object> activation
) {
}
