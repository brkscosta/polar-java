package sh.polar.sdk.models.checkout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a checkout link response from Polar API.
 * Used for checkout link endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarCheckoutLinkResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("payment_processor") String paymentProcessor,
        @JsonProperty("client_secret") String clientSecret,
        @JsonProperty("success_url") String successUrl,
        @JsonProperty("label") String label,
        @JsonProperty("allow_discount_codes") Boolean allowDiscountCodes,
        @JsonProperty("discount_id") UUID discountId,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("product_id") UUID productId,
        @JsonProperty("product_price_id") UUID productPriceId,
        @JsonProperty("url") String url,
        @JsonProperty("metadata") Map<String, Object> metadata
) {
}
