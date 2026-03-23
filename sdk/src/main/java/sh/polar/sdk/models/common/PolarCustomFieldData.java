package sh.polar.sdk.models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents custom field data from Polar (extensible container for future fields).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarCustomFieldData() {
}
