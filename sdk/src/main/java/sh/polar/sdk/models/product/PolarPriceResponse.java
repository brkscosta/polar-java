package sh.polar.sdk.models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Represents a price response from Polar API.
 * Used in product and subscription responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarPriceResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("amount_type") String amountType,
        @JsonProperty("is_archived") Boolean isArchived,
        @JsonProperty("product_id") UUID productId,
        @JsonProperty("type") String type,
        @JsonProperty("recurring_interval") String recurringInterval,
        @JsonProperty("price_amount") Integer priceAmount,
        @JsonProperty("price_currency") String priceCurrency
) {
}
