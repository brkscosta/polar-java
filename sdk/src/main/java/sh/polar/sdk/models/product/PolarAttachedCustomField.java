package sh.polar.sdk.models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Represents a custom field attached to a product from Polar.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarAttachedCustomField(
        @JsonProperty("custom_field_id") UUID customFieldId,
        @JsonProperty("custom_field") PolarCustomField customField,
        @JsonProperty("order") Integer order,
        @JsonProperty("required") Boolean required
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PolarCustomField(
            @JsonProperty("id") UUID id,
            @JsonProperty("type") String type,
            @JsonProperty("slug") String slug,
            @JsonProperty("name") String name,
            @JsonProperty("organization_id") UUID organizationId,
            @JsonProperty("metadata") Object metadata,
            @JsonProperty("properties") Object properties
    ) {
    }
}
