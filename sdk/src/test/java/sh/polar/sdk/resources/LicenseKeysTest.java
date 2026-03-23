package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.license.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class LicenseKeysTest {

    private PolarHttpClient http;
    private LicenseKeys licenseKeys;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        licenseKeys = new LicenseKeys(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/license-keys/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(licenseKeys.list(1, 10)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarLicenseKeyResponse.class);
        when(http.get("/license-keys/" + testId, PolarLicenseKeyResponse.class)).thenReturn(expected);
        assertThat(licenseKeys.get(testId)).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarLicenseKeyResponse.class);
        var body = Map.<String, Object>of("status", "disabled");
        when(http.patch("/license-keys/" + testId, body, PolarLicenseKeyResponse.class)).thenReturn(expected);
        assertThat(licenseKeys.update(testId, body)).isSameAs(expected);
    }

    @Test
    void activateDelegatesToHttp() {
        var expected = mock(PolarLicenseKeyActivationResponse.class);
        var body = Map.<String, Object>of("key", "LIC-KEY-123", "label", "device-1");
        when(http.post("/license-keys/activate", body, PolarLicenseKeyActivationResponse.class)).thenReturn(expected);
        assertThat(licenseKeys.activate(body)).isSameAs(expected);
    }

    @Test
    void deactivateDelegatesToHttp() {
        var body = Map.<String, Object>of("key", "LIC-KEY-123", "activation_id", "act-1");
        licenseKeys.deactivate(body);
        verify(http).post("/license-keys/deactivate", body);
    }

    @Test
    void validateDelegatesToHttp() {
        var expected = mock(PolarLicenseKeyValidationResponse.class);
        var body = Map.<String, Object>of("key", "LIC-KEY-123");
        when(http.post("/license-keys/validate", body, PolarLicenseKeyValidationResponse.class)).thenReturn(expected);
        assertThat(licenseKeys.validate(body)).isSameAs(expected);
    }
}
