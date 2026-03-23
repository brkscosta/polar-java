package sh.polar.sdk.models.customer;

import sh.polar.sdk.models.subscription.PolarSubscriptionResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a customer state response from Polar API.
 * Used for GET /customers/{id}/state and GET /customers/external/{id}/state endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarCustomerStateResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("email") String email,
        @JsonProperty("name") String name,
        @JsonProperty("metadata") Map<String, Object> metadata,
        @JsonProperty("active_subscriptions") List<PolarSubscriptionResponse> activeSubscriptions,
        @JsonProperty("granted_benefits") List<Map<String, Object>> grantedBenefits
) {
}
