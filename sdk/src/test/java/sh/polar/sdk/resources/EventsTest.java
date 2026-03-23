package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.event.PolarEventResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class EventsTest {

    private PolarHttpClient http;
    private Events events;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        events = new Events(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/events/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(events.list(1, 10)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarEventResponse.class);
        when(http.get("/events/" + testId, PolarEventResponse.class)).thenReturn(expected);
        assertThat(events.get(testId)).isSameAs(expected);
    }

    @Test
    void ingestDelegatesToHttp() {
        var body = Map.<String, Object>of("events", List.of(Map.of("name", "api_call")));
        events.ingest(body);
        verify(http).post("/events/ingest", body);
    }

    @Test
    void listNamesDelegatesToHttp() {
        var expected = List.of("api_call", "page_view");
        when(http.get(eq("/events/names"), any(TypeReference.class))).thenReturn(expected);
        assertThat(events.listNames()).containsExactly("api_call", "page_view");
    }
}
