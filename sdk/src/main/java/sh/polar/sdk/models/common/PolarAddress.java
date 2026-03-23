package sh.polar.sdk.models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an address from Polar API.
 * Used for billing addresses in customer and order responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarAddress(
        @JsonProperty("line1") String line1,
        @JsonProperty("line2") String line2,
        @JsonProperty("postal_code") String postalCode,
        @JsonProperty("city") String city,
        @JsonProperty("state") String state,
        @JsonProperty("country") String country
) {
}
