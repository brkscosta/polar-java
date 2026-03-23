package sh.polar.sdk.models.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Represents an order line item from Polar API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarOrderItem(
        @JsonProperty("id") UUID id,
        @JsonProperty("type") String type,
        @JsonProperty("label") String label,
        @JsonProperty("quantity") Integer quantity,
        @JsonProperty("price_id") UUID priceId,
        @JsonProperty("product_price_id") UUID productPriceId,
        @JsonProperty("unit_amount") Integer unitAmount,
        @JsonProperty("subtotal_amount") Integer subtotalAmount,
        @JsonProperty("discount_amount") Integer discountAmount,
        @JsonProperty("amount") Integer amount
) {
}
