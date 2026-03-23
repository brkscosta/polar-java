package sh.polar.sdk.models.license;

import sh.polar.sdk.models.customer.PolarCustomerResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a license key response from Polar API.
 * Used for license key endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarLicenseKeyResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("customer_id") UUID customerId,
        @JsonProperty("benefit_id") UUID benefitId,
        @JsonProperty("key") String key,
        @JsonProperty("display_key") String displayKey,
        @JsonProperty("status") String status,
        @JsonProperty("limit_activations") Integer limitActivations,
        @JsonProperty("usage") Integer usage,
        @JsonProperty("limit_usage") Integer limitUsage,
        @JsonProperty("validations") Integer validations,
        @JsonProperty("last_validated_at") OffsetDateTime lastValidatedAt,
        @JsonProperty("expires_at") OffsetDateTime expiresAt,
        @JsonProperty("conditions") Map<String, Object> conditions,
        @JsonProperty("customer") PolarCustomerResponse customer
) {
}
