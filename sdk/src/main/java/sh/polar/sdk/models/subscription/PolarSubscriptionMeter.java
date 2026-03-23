package sh.polar.sdk.models.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Represents a meter attached to a subscription from Polar.
 * Meters track usage-based billing.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarSubscriptionMeter(
        @JsonProperty("meter_id") UUID meterId,
        @JsonProperty("consumed_units") Long consumedUnits,
        @JsonProperty("credited_units") Long creditedUnits,
        @JsonProperty("balance") Long balance
) {
}
