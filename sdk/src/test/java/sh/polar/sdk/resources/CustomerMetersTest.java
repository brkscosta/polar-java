package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.meter.PolarCustomerMeterResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CustomerMetersTest {

    private PolarHttpClient http;
    private CustomerMeters customerMeters;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        customerMeters = new CustomerMeters(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/customer-meters/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(customerMeters.list(1, 10)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarCustomerMeterResponse.class);
        when(http.get("/customer-meters/" + testId, PolarCustomerMeterResponse.class)).thenReturn(expected);
        assertThat(customerMeters.get(testId)).isSameAs(expected);
    }
}
