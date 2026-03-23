package sh.polar.sdk.models.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Represents user data from Polar.
 * Contains basic user profile information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarUser(
        @JsonProperty("id") UUID id,
        @JsonProperty("email") String email,
        @JsonProperty("public_name") String publicName,
        @JsonProperty("avatar_url") String avatarUrl,
        @JsonProperty("github_username") String githubUsername
) {
}
