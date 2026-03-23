package sh.polar.sdk.models.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Represents custom metadata from Polar subscription.
 * Contains the user_id mapping to link subscriptions to application users.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarMetadata(
        @JsonProperty("user_id") UUID userId
) {
}
