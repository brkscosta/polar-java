package sh.polar.sdk.models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Represents a product benefit from Polar.
 * Benefits are features or perks included with a product.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarBenefit(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("type") String type,
        @JsonProperty("description") String description,
        @JsonProperty("selectable") Boolean selectable,
        @JsonProperty("deletable") Boolean deletable,
        @JsonProperty("organization_id") UUID organizationId
) {
}
