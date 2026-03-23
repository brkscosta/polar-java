package sh.polar.sdk.models.meter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a meter response from Polar API.
 * Used for meter endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarMeterResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("name") String name,
        @JsonProperty("slug") String slug,
        @JsonProperty("filter") Map<String, Object> filter,
        @JsonProperty("aggregation") Map<String, Object> aggregation,
        @JsonProperty("organization_id") UUID organizationId
) {
}
