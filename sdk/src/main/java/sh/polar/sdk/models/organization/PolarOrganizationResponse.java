package sh.polar.sdk.models.organization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents an organization response from Polar API.
 * Used for organization endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarOrganizationResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("name") String name,
        @JsonProperty("slug") String slug,
        @JsonProperty("avatar_url") String avatarUrl,
        @JsonProperty("bio") String bio,
        @JsonProperty("company") String company,
        @JsonProperty("blog") String blog,
        @JsonProperty("location") String location,
        @JsonProperty("email") String email,
        @JsonProperty("twitter_username") String twitterUsername,
        @JsonProperty("feature_settings") Map<String, Object> featureSettings
) {
}
