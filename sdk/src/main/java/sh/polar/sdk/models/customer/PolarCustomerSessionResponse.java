package sh.polar.sdk.models.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a customer session response from Polar API.
 * Used for POST /customer-sessions/ endpoint response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarCustomerSessionResponse(
        @JsonProperty("id") String id,
        @JsonProperty("token") String token,
        @JsonProperty("expires_at") String expiresAt,
        @JsonProperty("customer_id") String customerId,
        @JsonProperty("customer_portal_url") String customerPortalUrl
) {
}
