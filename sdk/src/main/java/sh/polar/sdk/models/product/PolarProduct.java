package sh.polar.sdk.models.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import sh.polar.sdk.models.common.PolarCustomFieldData;
import sh.polar.sdk.models.common.PolarPrice;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Represents product data from Polar.
 * A product is a sellable item that can be recurring (subscription) or one-time.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarProduct(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("trial_interval") String trialInterval,
        @JsonProperty("trial_interval_count") Long trialIntervalCount,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("visibility") String visibility,
        @JsonProperty("recurring_interval") String recurringInterval,
        @JsonProperty("recurring_interval_count") Long recurringIntervalCount,
        @JsonProperty("is_recurring") Boolean isRecurring,
        @JsonProperty("is_archived") Boolean isArchived,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("metadata") PolarCustomFieldData metadata,
        @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        @JsonProperty("prices") List<PolarPrice> prices,
        @JsonProperty("benefits") List<PolarBenefit> benefits,
        @JsonProperty("medias") List<PolarMedia> medias,
        @JsonProperty("attached_custom_fields") List<PolarAttachedCustomField> attachedCustomFields
) {
}
