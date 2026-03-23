package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.meter.PolarMeterResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class MetersTest {

    private PolarHttpClient http;
    private Meters meters;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        meters = new Meters(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/meters/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(meters.list(1, 10)).isSameAs(expected);
    }

    @Test
    void createDelegatesToHttp() {
        var expected = mock(PolarMeterResponse.class);
        var body = Map.<String, Object>of("name", "API Calls");
        when(http.post("/meters/", body, PolarMeterResponse.class)).thenReturn(expected);
        assertThat(meters.create(body)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarMeterResponse.class);
        when(http.get("/meters/" + testId, PolarMeterResponse.class)).thenReturn(expected);
        assertThat(meters.get(testId)).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarMeterResponse.class);
        var body = Map.<String, Object>of("name", "Updated Meter");
        when(http.patch("/meters/" + testId, body, PolarMeterResponse.class)).thenReturn(expected);
        assertThat(meters.update(testId, body)).isSameAs(expected);
    }

    @Test
    void getQuantitiesDelegatesToHttp() {
        var expected = Map.of("total", (Object) 100);
        when(http.get(eq("/meters/" + testId + "/quantities"), any(TypeReference.class))).thenReturn(expected);
        assertThat(meters.getQuantities(testId)).containsEntry("total", 100);
    }
}
