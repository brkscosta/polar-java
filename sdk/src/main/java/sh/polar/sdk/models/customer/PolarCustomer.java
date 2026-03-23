package sh.polar.sdk.models.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import sh.polar.sdk.models.common.PolarAddress;
import sh.polar.sdk.models.common.PolarCustomFieldData;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Represents customer data from Polar.
 * Contains billing and contact information for a customer.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarCustomer(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("metadata") PolarCustomFieldData metadata,
        @JsonProperty("external_id") String externalId,
        @JsonProperty("email") String email,
        @JsonProperty("email_verified") boolean emailVerified,
        @JsonProperty("type") String type,
        @JsonProperty("name") String name,
        @JsonProperty("billing_address") PolarAddress billingAddress,
        @JsonProperty("tax_id") String taxId,
        @JsonProperty("locale") String locale,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("deleted_at") OffsetDateTime deletedAt,
        @JsonProperty("avatar_url") String avatarUrl
) {
}
