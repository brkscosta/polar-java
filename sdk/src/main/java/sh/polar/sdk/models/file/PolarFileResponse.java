package sh.polar.sdk.models.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Represents a file response from Polar API.
 * Used for file endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarFileResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("organization_id") UUID organizationId,
        @JsonProperty("name") String name,
        @JsonProperty("path") String path,
        @JsonProperty("mime_type") String mimeType,
        @JsonProperty("size") Long size,
        @JsonProperty("storage_version") String storageVersion,
        @JsonProperty("checksum_etag") String checksumEtag,
        @JsonProperty("checksum_sha256_base64") String checksumSha256Base64,
        @JsonProperty("checksum_sha256_hex") String checksumSha256Hex,
        @JsonProperty("last_modified_at") OffsetDateTime lastModifiedAt,
        @JsonProperty("version") String version,
        @JsonProperty("service") String service,
        @JsonProperty("is_uploaded") Boolean isUploaded,
        @JsonProperty("upload") Object upload,
        @JsonProperty("public_url") String publicUrl
) {
}
