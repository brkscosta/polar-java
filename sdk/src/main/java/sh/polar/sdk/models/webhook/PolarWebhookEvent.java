package sh.polar.sdk.models.webhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import sh.polar.sdk.models.subscription.PolarSubscription;

import java.time.OffsetDateTime;

/**
 * Represents a Polar webhook event payload.
 * <p>
 * Supported subscription events:
 * - subscription.active: Subscription became active
 * - subscription.updated: Subscription was modified
 * - subscription.canceled: Subscription was canceled
 * - subscription.revoked: Subscription access was immediately revoked
 *
 * @param type      the event type (e.g., "subscription.active", "subscription.canceled")
 * @param timestamp the timestamp when the event occurred
 * @param data      the subscription data associated with the event
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarWebhookEvent(
        @JsonProperty("type") String type,
        @JsonProperty("timestamp") OffsetDateTime timestamp,
        @JsonProperty("data") PolarSubscription data
) {
}
