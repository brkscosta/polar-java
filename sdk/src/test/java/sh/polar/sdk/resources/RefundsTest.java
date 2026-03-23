package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.payment.PolarRefundResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RefundsTest {

    private PolarHttpClient http;
    private Refunds refunds;

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        refunds = new Refunds(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/refunds/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(refunds.list(1, 10)).isSameAs(expected);
    }

    @Test
    void createDelegatesToHttp() {
        var expected = mock(PolarRefundResponse.class);
        var body = Map.<String, Object>of("payment_id", "pay_123", "reason", "customer_request");
        when(http.post("/refunds/", body, PolarRefundResponse.class)).thenReturn(expected);
        assertThat(refunds.create(body)).isSameAs(expected);
    }
}
