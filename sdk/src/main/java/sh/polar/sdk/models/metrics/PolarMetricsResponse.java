package sh.polar.sdk.models.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Represents a metrics response from Polar API.
 * Used for GET /metrics/ endpoint response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarMetricsResponse(
        @JsonProperty("periods") List<PolarMetricPeriod> periods,
        @JsonProperty("metrics") Map<String, PolarMetricData> metrics
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PolarMetricPeriod(
            @JsonProperty("start_timestamp") String startTimestamp,
            @JsonProperty("end_timestamp") String endTimestamp
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PolarMetricData(
            @JsonProperty("slug") String slug,
            @JsonProperty("display_name") String displayName,
            @JsonProperty("type") String type,
            @JsonProperty("data") List<Map<String, Object>> data
    ) {
    }
}
