package sh.polar.sdk.models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Represents price data from Polar.
 * Used in products and subscriptions.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarPrice(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("source") String source,
        @JsonProperty("amount_type") String amountType,
        @JsonProperty("price_currency") String priceCurrency,
        @JsonProperty("is_archived") boolean isArchived,
        @JsonProperty("product_id") UUID productId,
        @JsonProperty("type") String type,
        @JsonProperty("recurring_interval") String recurringInterval,
        @JsonProperty("price_amount") long priceAmount
) {
}
