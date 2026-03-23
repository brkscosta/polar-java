package sh.polar.sdk.models.customfield;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a custom field response from Polar API.
 * Used for custom field endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarCustomFieldResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("type") String type,
        @JsonProperty("slug") String slug,
        @JsonProperty("name") String name,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("metadata") Map<String, Object> metadata,
        @JsonProperty("properties") Map<String, Object> properties
) {
}
