package sh.polar.sdk.models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a product response from Polar API.
 * Used in subscription and order responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarProductResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("visibility") String visibility,
        @JsonProperty("is_recurring") Boolean isRecurring,
        @JsonProperty("is_archived") Boolean isArchived,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("trial_interval") String trialInterval,
        @JsonProperty("trial_interval_count") Integer trialIntervalCount,
        @JsonProperty("metadata") Map<String, Object> metadata,
        @JsonProperty("prices") List<PolarPriceResponse> prices,
        @JsonProperty("benefits") List<Object> benefits,
        @JsonProperty("medias") List<Object> medias
) {
}
