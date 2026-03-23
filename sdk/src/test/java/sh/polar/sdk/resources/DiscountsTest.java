package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.discount.PolarDiscountResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class DiscountsTest {

    private PolarHttpClient http;
    private Discounts discounts;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        discounts = new Discounts(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/discounts/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(discounts.list(1, 10)).isSameAs(expected);
    }

    @Test
    void createDelegatesToHttp() {
        var expected = mock(PolarDiscountResponse.class);
        var body = Map.<String, Object>of("name", "10OFF", "type", "percentage", "amount", 10);
        when(http.post("/discounts/", body, PolarDiscountResponse.class)).thenReturn(expected);
        assertThat(discounts.create(body)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarDiscountResponse.class);
        when(http.get("/discounts/" + testId, PolarDiscountResponse.class)).thenReturn(expected);
        assertThat(discounts.get(testId)).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarDiscountResponse.class);
        var body = Map.<String, Object>of("name", "20OFF");
        when(http.patch("/discounts/" + testId, body, PolarDiscountResponse.class)).thenReturn(expected);
        assertThat(discounts.update(testId, body)).isSameAs(expected);
    }

    @Test
    void deleteDelegatesToHttp() {
        discounts.delete(testId);
        verify(http).delete("/discounts/" + testId);
    }
}
