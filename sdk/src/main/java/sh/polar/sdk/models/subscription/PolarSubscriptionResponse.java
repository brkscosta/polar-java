package sh.polar.sdk.models.subscription;

import sh.polar.sdk.models.customer.PolarCustomerResponse;
import sh.polar.sdk.models.product.PolarProductResponse;
import sh.polar.sdk.models.product.PolarPriceResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a subscription response from Polar API.
 * Used for GET /subscriptions/ endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarSubscriptionResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("amount") Integer amount,
        @JsonProperty("currency") String currency,
        @JsonProperty("recurring_interval") String recurringInterval,
        @JsonProperty("recurring_interval_count") Integer recurringIntervalCount,
        @JsonProperty("status") String status,
        @JsonProperty("current_period_start") OffsetDateTime currentPeriodStart,
        @JsonProperty("current_period_end") OffsetDateTime currentPeriodEnd,
        @JsonProperty("trial_start") OffsetDateTime trialStart,
        @JsonProperty("trial_end") OffsetDateTime trialEnd,
        @JsonProperty("cancel_at_period_end") Boolean cancelAtPeriodEnd,
        @JsonProperty("canceled_at") OffsetDateTime canceledAt,
        @JsonProperty("started_at") OffsetDateTime startedAt,
        @JsonProperty("ends_at") OffsetDateTime endsAt,
        @JsonProperty("ended_at") OffsetDateTime endedAt,
        @JsonProperty("customer_id") UUID customerId,
        @JsonProperty("product_id") UUID productId,
        @JsonProperty("discount_id") UUID discountId,
        @JsonProperty("checkout_id") UUID checkoutId,
        @JsonProperty("seats") Integer seats,
        @JsonProperty("customer_cancellation_reason") String customerCancellationReason,
        @JsonProperty("customer_cancellation_comment") String customerCancellationComment,
        @JsonProperty("metadata") Map<String, Object> metadata,
        @JsonProperty("custom_field_data") Map<String, Object> customFieldData,
        @JsonProperty("customer") PolarCustomerResponse customer,
        @JsonProperty("product") PolarProductResponse product,
        @JsonProperty("prices") List<PolarPriceResponse> prices
) {
}
