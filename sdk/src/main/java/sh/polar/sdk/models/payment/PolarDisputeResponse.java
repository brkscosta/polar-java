package sh.polar.sdk.models.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Represents a dispute response from Polar API.
 * Used for dispute endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarDisputeResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("amount") Integer amount,
        @JsonProperty("currency") String currency,
        @JsonProperty("reason") String reason,
        @JsonProperty("status") String status,
        @JsonProperty("payment_id") UUID paymentId,
        @JsonProperty("organization_id") UUID organizationId
) {
}
