package sh.polar.sdk.models.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Represents the metrics limits response from Polar API.
 * Used for GET /metrics/limits endpoint response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarMetricsLimitsResponse(
        @JsonProperty("intervals") Map<String, PolarIntervalLimit> intervals
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PolarIntervalLimit(
            @JsonProperty("max_days") Integer maxDays
    ) {
    }
}
