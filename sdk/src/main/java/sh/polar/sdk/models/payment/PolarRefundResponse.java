package sh.polar.sdk.models.payment;

import sh.polar.sdk.models.order.PolarOrderResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Represents a refund response from Polar API.
 * Used for refund endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarRefundResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("status") String status,
        @JsonProperty("reason") String reason,
        @JsonProperty("comment") String comment,
        @JsonProperty("amount") Integer amount,
        @JsonProperty("tax_amount") Integer taxAmount,
        @JsonProperty("currency") String currency,
        @JsonProperty("order_id") UUID orderId,
        @JsonProperty("subscription_id") UUID subscriptionId,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("order") PolarOrderResponse order
) {
}
