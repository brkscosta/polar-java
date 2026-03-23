package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.checkout.PolarCheckoutResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CheckoutsTest {

    private PolarHttpClient http;
    private Checkouts checkouts;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        checkouts = new Checkouts(http);
    }

    @Test
    void createDelegatesToHttp() {
        var expected = mock(PolarCheckoutResponse.class);
        Map<String, Object> body = Map.of("products", List.of("prod_1"));
        when(http.post(eq("/checkouts/"), eq(body), eq(PolarCheckoutResponse.class)))
                .thenReturn(expected);

        var result = checkouts.create(body);
        assertThat(result).isSameAs(expected);
    }

    @Test
    void createConvenienceMethod() {
        var expected = mock(PolarCheckoutResponse.class);
        when(http.post(eq("/checkouts/"), any(Map.class), eq(PolarCheckoutResponse.class)))
                .thenReturn(expected);

        var result = checkouts.create("prod_1", "test@test.com", "https://ok.com", null);
        assertThat(result).isSameAs(expected);
        verify(http).post(eq("/checkouts/"), argThat(body -> {
            Map<String, Object> m = (Map<String, Object>) body;
            return m.containsKey("products") && m.containsKey("success_url");
        }), eq(PolarCheckoutResponse.class));
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/checkouts/?page=1&limit=10"), any(TypeReference.class)))
                .thenReturn(expected);

        var result = checkouts.list(1, 10);
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarCheckoutResponse.class);
        when(http.get("/checkouts/" + testId, PolarCheckoutResponse.class))
                .thenReturn(expected);

        var result = checkouts.get(testId);
        assertThat(result).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarCheckoutResponse.class);
        Map<String, Object> body = Map.of("metadata", Map.of("key", "val"));
        when(http.patch(eq("/checkouts/" + testId), eq(body), eq(PolarCheckoutResponse.class)))
                .thenReturn(expected);

        var result = checkouts.update(testId, body);
        assertThat(result).isSameAs(expected);
    }
}
