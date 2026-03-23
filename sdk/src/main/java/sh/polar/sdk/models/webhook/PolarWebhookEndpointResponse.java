package sh.polar.sdk.models.webhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a webhook endpoint response from Polar API.
 * Used for webhook management endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarWebhookEndpointResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("url") String url,
        @JsonProperty("format") String format,
        @JsonProperty("events") List<String> events,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("secret") String secret
) {
}
