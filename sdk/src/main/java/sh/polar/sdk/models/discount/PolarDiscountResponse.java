package sh.polar.sdk.models.discount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a discount response from Polar API.
 * Used for discount endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarDiscountResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("name") String name,
        @JsonProperty("code") String code,
        @JsonProperty("type") String type,
        @JsonProperty("basis_points") Integer basisPoints,
        @JsonProperty("amount") Long amount,
        @JsonProperty("currency") String currency,
        @JsonProperty("duration") String duration,
        @JsonProperty("duration_in_months") Integer durationInMonths,
        @JsonProperty("max_redemptions") Integer maxRedemptions,
        @JsonProperty("redemptions_count") Integer redemptionsCount,
        @JsonProperty("starts_at") OffsetDateTime startsAt,
        @JsonProperty("ends_at") OffsetDateTime endsAt,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("metadata") Map<String, Object> metadata
) {
}
