package sh.polar.sdk.models.checkout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a checkout session response from Polar API.
 * Used for POST /checkouts/ endpoint response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarCheckoutResponse(
        @JsonProperty("id") String id,
        @JsonProperty("url") String url,
        @JsonProperty("status") String status,
        @JsonProperty("client_secret") String clientSecret,
        @JsonProperty("payment_processor") String paymentProcessor,
        @JsonProperty("embed_origin") String embedOrigin
) {
}
