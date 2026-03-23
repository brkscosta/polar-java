package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.payment.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PaymentsTest {

    private PolarHttpClient http;
    private Payments payments;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        payments = new Payments(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/payments/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(payments.list(1, 10)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarPaymentResponse.class);
        when(http.get("/payments/" + testId, PolarPaymentResponse.class)).thenReturn(expected);
        assertThat(payments.get(testId)).isSameAs(expected);
    }
}
