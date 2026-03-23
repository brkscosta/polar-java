package sh.polar.sdk.models.payment;

import sh.polar.sdk.models.customer.PolarCustomerResponse;
import sh.polar.sdk.models.order.PolarOrderResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Represents a payment response from Polar API.
 * Used for GET /payments/ and GET /payments/{id} endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarPaymentResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("amount") Integer amount,
        @JsonProperty("currency") String currency,
        @JsonProperty("status") String status,
        @JsonProperty("method") String method,
        @JsonProperty("processor") String processor,
        @JsonProperty("customer_id") UUID customerId,
        @JsonProperty("order_id") UUID orderId,
        @JsonProperty("subscription_id") UUID subscriptionId,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("customer") PolarCustomerResponse customer,
        @JsonProperty("order") PolarOrderResponse order
) {
}
