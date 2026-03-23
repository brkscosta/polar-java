package sh.polar.sdk.models.subscription;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import sh.polar.sdk.models.common.PolarCustomFieldData;
import sh.polar.sdk.models.common.PolarDiscount;
import sh.polar.sdk.models.common.PolarPrice;
import sh.polar.sdk.models.customer.PolarCustomer;
import sh.polar.sdk.models.customer.PolarUser;
import sh.polar.sdk.models.product.PolarProduct;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Represents subscription data from a Polar webhook event.
 * <p>
 * Subscription statuses:
 * - incomplete: Initial state, awaiting payment
 * - incomplete_expired: Payment failed, subscription expired
 * - trialing: Customer is in trial period
 * - active: Subscription is active and paid
 * - past_due: Payment failed but subscription still active
 * - canceled: Subscription was canceled (may still have access until period end)
 * - unpaid: Multiple payment failures
 * - paused: Subscription temporarily paused
 * <p>
 * Events:
 * - subscription.active: Fired when subscription becomes active
 * - subscription.updated: Fired when subscription is modified (plan change, etc.)
 * - subscription.canceled: Fired when subscription is canceled
 * - subscription.revoked: Fired when access is immediately revoked
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarSubscription(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("amount") Long amount,
        @JsonProperty("currency") String currency,
        @JsonProperty("recurring_interval") String recurringInterval,
        @JsonProperty("recurring_interval_count") Long recurringIntervalCount,
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
        @JsonProperty("price_id") UUID priceId,
        @JsonProperty("metadata") PolarMetadata metadata,
        @JsonProperty("custom_field_data") PolarCustomFieldData customFieldData,
        @JsonProperty("customer") PolarCustomer customer,
        @JsonProperty("user_id") UUID userId,
        @JsonProperty("user") PolarUser user,
        @JsonProperty("product") PolarProduct product,
        @JsonProperty("discount") PolarDiscount discount,
        @JsonProperty("price") PolarPrice price,
        @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        @JsonProperty("prices") List<PolarPrice> prices,
        @JsonProperty("meters") List<PolarSubscriptionMeter> meters
) {
}
