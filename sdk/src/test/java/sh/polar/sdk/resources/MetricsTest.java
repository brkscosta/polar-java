package sh.polar.sdk.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.metrics.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class MetricsTest {

    private PolarHttpClient http;
    private Metrics metrics;

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        metrics = new Metrics(http);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarMetricsResponse.class);
        // Params sorted alphabetically: end_date, interval, start_date
        when(http.get(eq("/metrics/?start_date=2024-01-01&end_date=2024-12-31&interval=month"),
                eq(PolarMetricsResponse.class))).thenReturn(expected);

        // LinkedHashMap preserves insertion order
        var params = new LinkedHashMap<String, String>();
        params.put("start_date", "2024-01-01");
        params.put("end_date", "2024-12-31");
        params.put("interval", "month");

        var result = metrics.get(params);
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getLimitsDelegatesToHttp() {
        var expected = mock(PolarMetricsLimitsResponse.class);
        when(http.get("/metrics/limits", PolarMetricsLimitsResponse.class)).thenReturn(expected);
        assertThat(metrics.getLimits()).isSameAs(expected);
    }
}
