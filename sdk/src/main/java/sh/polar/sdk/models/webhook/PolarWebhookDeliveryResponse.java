package sh.polar.sdk.models.webhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a webhook delivery response from Polar API.
 * Used for GET /webhooks/deliveries endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarWebhookDeliveryResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("webhook_endpoint_id") UUID webhookEndpointId,
        @JsonProperty("webhook_event_id") UUID webhookEventId,
        @JsonProperty("http_code") Integer httpCode,
        @JsonProperty("succeeded") Boolean succeeded
) {
}
