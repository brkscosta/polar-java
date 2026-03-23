package sh.polar.sdk.resources;

import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.metrics.*;

import java.util.*;
import java.util.stream.Collectors;

public class Metrics {
    private final PolarHttpClient http;

    public Metrics(PolarHttpClient http) { this.http = http; }

    public PolarMetricsResponse get(Map<String, String> params) {
        String query = params.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
        return http.get("/metrics/?" + query, PolarMetricsResponse.class);
    }

    public PolarMetricsLimitsResponse getLimits() {
        return http.get("/metrics/limits", PolarMetricsLimitsResponse.class);
    }
}
