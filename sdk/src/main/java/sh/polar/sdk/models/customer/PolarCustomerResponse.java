package sh.polar.sdk.models.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import sh.polar.sdk.models.common.PolarAddress;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a customer response from Polar API.
 * Used in subscription and order responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarCustomerResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("metadata") Map<String, Object> metadata,
        @JsonProperty("external_id") String externalId,
        @JsonProperty("email") String email,
        @JsonProperty("email_verified") Boolean emailVerified,
        @JsonProperty("type") String type,
        @JsonProperty("name") String name,
        @JsonProperty("billing_address") PolarAddress billingAddress,
        @JsonProperty("tax_id") Object taxId,
        @JsonProperty("locale") String locale,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("deleted_at") OffsetDateTime deletedAt,
        @JsonProperty("avatar_url") String avatarUrl
) {
}
