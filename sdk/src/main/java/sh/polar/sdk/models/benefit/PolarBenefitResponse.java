package sh.polar.sdk.models.benefit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a benefit response from Polar API.
 * Used for benefits CRUD endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarBenefitResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("type") String type,
        @JsonProperty("description") String description,
        @JsonProperty("selectable") Boolean selectable,
        @JsonProperty("deletable") Boolean deletable,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("properties") Map<String, Object> properties
) {
}
