package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.benefit.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class BenefitsTest {

    private PolarHttpClient http;
    private Benefits benefits;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        benefits = new Benefits(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/benefits/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(benefits.list(1, 10)).isSameAs(expected);
    }

    @Test
    void createDelegatesToHttp() {
        var expected = mock(PolarBenefitResponse.class);
        var body = Map.<String, Object>of("type", "custom", "description", "Test");
        when(http.post("/benefits/", body, PolarBenefitResponse.class)).thenReturn(expected);
        assertThat(benefits.create(body)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarBenefitResponse.class);
        when(http.get("/benefits/" + testId, PolarBenefitResponse.class)).thenReturn(expected);
        assertThat(benefits.get(testId)).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarBenefitResponse.class);
        var body = Map.<String, Object>of("description", "Updated");
        when(http.patch("/benefits/" + testId, body, PolarBenefitResponse.class)).thenReturn(expected);
        assertThat(benefits.update(testId, body)).isSameAs(expected);
    }

    @Test
    void deleteDelegatesToHttp() {
        benefits.delete(testId);
        verify(http).delete("/benefits/" + testId);
    }

    @Test
    void listGrantsDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/benefit-grants/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(benefits.listGrants(1, 10)).isSameAs(expected);
    }

    @Test
    void listGrantsByBenefitDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/benefits/" + testId + "/grants?page=1&limit=10"), any(TypeReference.class)))
                .thenReturn(expected);
        assertThat(benefits.listGrantsByBenefit(testId, 1, 10)).isSameAs(expected);
    }
}
