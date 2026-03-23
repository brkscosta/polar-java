package sh.polar.sdk.models.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents an event response from Polar API.
 * Used for event endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarEventResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("name") String name,
        @JsonProperty("source") String source,
        @JsonProperty("customer_id") UUID customerId,
        @JsonProperty("external_customer_id") String externalCustomerId,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("metadata") Map<String, Object> metadata
) {
}
