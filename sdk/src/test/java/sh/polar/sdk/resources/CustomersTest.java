package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.customer.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CustomersTest {

    private PolarHttpClient http;
    private Customers customers;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        customers = new Customers(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/customers/?page=1&limit=50"), any(TypeReference.class)))
                .thenReturn(expected);
        assertThat(customers.list(1, 50)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarCustomerResponse.class);
        when(http.get("/customers/" + testId, PolarCustomerResponse.class))
                .thenReturn(expected);
        assertThat(customers.get(testId)).isSameAs(expected);
    }

    @Test
    void createDelegatesToHttp() {
        var expected = mock(PolarCustomerResponse.class);
        var body = Map.<String, Object>of("email", "test@test.com");
        when(http.post("/customers/", body, PolarCustomerResponse.class))
                .thenReturn(expected);
        assertThat(customers.create(body)).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarCustomerResponse.class);
        var body = Map.<String, Object>of("name", "Updated");
        when(http.patch("/customers/" + testId, body, PolarCustomerResponse.class))
                .thenReturn(expected);
        assertThat(customers.update(testId, body)).isSameAs(expected);
    }

    @Test
    void deleteDelegatesToHttp() {
        customers.delete(testId);
        verify(http).delete("/customers/" + testId);
    }

    @Test
    void getStateDelegatesToHttp() {
        var expected = mock(PolarCustomerStateResponse.class);
        when(http.get("/customers/" + testId + "/state", PolarCustomerStateResponse.class))
                .thenReturn(expected);
        assertThat(customers.getState(testId)).isSameAs(expected);
    }

    @Test
    void getByExternalIdDelegatesToHttp() {
        var expected = mock(PolarCustomerResponse.class);
        when(http.get("/customers/external/ext-123", PolarCustomerResponse.class))
                .thenReturn(expected);
        assertThat(customers.getByExternalId("ext-123")).isSameAs(expected);
    }

    @Test
    void updateByExternalIdDelegatesToHttp() {
        var expected = mock(PolarCustomerResponse.class);
        var body = Map.<String, Object>of("name", "Updated");
        when(http.patch("/customers/external/ext-123", body, PolarCustomerResponse.class))
                .thenReturn(expected);
        assertThat(customers.updateByExternalId("ext-123", body)).isSameAs(expected);
    }

    @Test
    void deleteByExternalIdDelegatesToHttp() {
        customers.deleteByExternalId("ext-123");
        verify(http).delete("/customers/external/ext-123");
    }

    @Test
    void getStateByExternalIdDelegatesToHttp() {
        var expected = mock(PolarCustomerStateResponse.class);
        when(http.get("/customers/external/ext-123/state", PolarCustomerStateResponse.class))
                .thenReturn(expected);
        assertThat(customers.getStateByExternalId("ext-123")).isSameAs(expected);
    }

    @Test
    void findByEmailReturnsPresent() {
        var customer = mock(PolarCustomerResponse.class);
        var listResponse = new PolarListResponse<>(List.of(customer), null);
        when(http.get(eq("/customers/?email=test@test.com&limit=1"), any(TypeReference.class)))
                .thenReturn(listResponse);
        assertThat(customers.findByEmail("test@test.com")).isPresent().containsSame(customer);
    }

    @Test
    void findByEmailReturnsEmpty() {
        var listResponse = new PolarListResponse<PolarCustomerResponse>(List.of(), null);
        when(http.get(eq("/customers/?email=missing@test.com&limit=1"), any(TypeReference.class)))
                .thenReturn(listResponse);
        assertThat(customers.findByEmail("missing@test.com")).isEmpty();
    }

    @Test
    void findByEmailReturnsEmptyOnException() {
        when(http.get(anyString(), any(TypeReference.class)))
                .thenThrow(new RuntimeException("network error"));
        assertThat(customers.findByEmail("error@test.com")).isEmpty();
    }

    @Test
    void createSessionDelegatesToHttp() {
        var expected = mock(PolarCustomerSessionResponse.class);
        when(http.post(eq("/customer-sessions/"), any(Map.class), eq(PolarCustomerSessionResponse.class)))
                .thenReturn(expected);
        assertThat(customers.createSession(testId)).isSameAs(expected);
    }
}
