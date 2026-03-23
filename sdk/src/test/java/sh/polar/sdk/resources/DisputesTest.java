package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.payment.PolarDisputeResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class DisputesTest {

    private PolarHttpClient http;
    private Disputes disputes;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        disputes = new Disputes(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/disputes/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(disputes.list(1, 10)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarDisputeResponse.class);
        when(http.get("/disputes/" + testId, PolarDisputeResponse.class)).thenReturn(expected);
        assertThat(disputes.get(testId)).isSameAs(expected);
    }
}
