package sh.polar.sdk.models.benefit;

import sh.polar.sdk.models.customer.PolarCustomerResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a benefit grant response from Polar API.
 * Used for GET /benefit-grants/ endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarBenefitGrantResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("is_granted") Boolean isGranted,
        @JsonProperty("is_revoked") Boolean isRevoked,
        @JsonProperty("granted_at") OffsetDateTime grantedAt,
        @JsonProperty("revoked_at") OffsetDateTime revokedAt,
        @JsonProperty("customer_id") UUID customerId,
        @JsonProperty("benefit_id") UUID benefitId,
        @JsonProperty("subscription_id") UUID subscriptionId,
        @JsonProperty("order_id") UUID orderId,
        @JsonProperty("properties") Map<String, Object> properties,
        @JsonProperty("customer") PolarCustomerResponse customer,
        @JsonProperty("benefit") PolarBenefitResponse benefit
) {
}
