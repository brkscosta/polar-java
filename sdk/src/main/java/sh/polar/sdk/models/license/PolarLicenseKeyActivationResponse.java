package sh.polar.sdk.models.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a license key activation response from Polar API.
 * Used for POST /license-keys/activate endpoint response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarLicenseKeyActivationResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("license_key_id") UUID licenseKeyId,
        @JsonProperty("label") String label,
        @JsonProperty("meta") Map<String, Object> meta,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("license_key") PolarLicenseKeyResponse licenseKey
) {
}
