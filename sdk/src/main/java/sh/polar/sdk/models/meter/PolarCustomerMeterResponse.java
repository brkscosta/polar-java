package sh.polar.sdk.models.meter;

import sh.polar.sdk.models.customer.PolarCustomerResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Represents a customer meter response from Polar API.
 * Used for GET /customer-meters/ and GET /customer-meters/{id} endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarCustomerMeterResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("customer_id") UUID customerId,
        @JsonProperty("meter_id") UUID meterId,
        @JsonProperty("consumed_units") Double consumedUnits,
        @JsonProperty("credited_units") Double creditedUnits,
        @JsonProperty("balance") Double balance,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("customer") PolarCustomerResponse customer,
        @JsonProperty("meter") PolarMeterResponse meter
) {
}
