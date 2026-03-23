package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.order.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class OrdersTest {

    private PolarHttpClient http;
    private Orders orders;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        orders = new Orders(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/orders/?customer_id=" + testId + "&limit=10"), any(TypeReference.class)))
                .thenReturn(expected);
        assertThat(orders.list(testId, 10)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarOrderResponse.class);
        when(http.get("/orders/" + testId, PolarOrderResponse.class)).thenReturn(expected);
        assertThat(orders.get(testId)).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarOrderResponse.class);
        var body = Map.<String, Object>of("metadata", Map.of());
        when(http.patch("/orders/" + testId, body, PolarOrderResponse.class)).thenReturn(expected);
        assertThat(orders.update(testId, body)).isSameAs(expected);
    }

    @Test
    void getInvoiceDelegatesToHttp() {
        var expected = mock(PolarInvoiceResponse.class);
        when(http.get("/orders/" + testId + "/invoice", PolarInvoiceResponse.class)).thenReturn(expected);
        assertThat(orders.getInvoice(testId)).isSameAs(expected);
    }

    @Test
    void generateInvoiceDelegatesToHttp() {
        var expected = mock(PolarInvoiceResponse.class);
        when(http.post("/orders/" + testId + "/invoice", null, PolarInvoiceResponse.class)).thenReturn(expected);
        assertThat(orders.generateInvoice(testId)).isSameAs(expected);
    }
}
