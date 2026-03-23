package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.organization.PolarOrganizationResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class OrganizationsTest {

    private PolarHttpClient http;
    private Organizations organizations;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        organizations = new Organizations(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/organizations/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(organizations.list(1, 10)).isSameAs(expected);
    }

    @Test
    void createDelegatesToHttp() {
        var expected = mock(PolarOrganizationResponse.class);
        var body = Map.<String, Object>of("name", "My Org", "slug", "my-org");
        when(http.post("/organizations/", body, PolarOrganizationResponse.class)).thenReturn(expected);
        assertThat(organizations.create(body)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarOrganizationResponse.class);
        when(http.get("/organizations/" + testId, PolarOrganizationResponse.class)).thenReturn(expected);
        assertThat(organizations.get(testId)).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarOrganizationResponse.class);
        var body = Map.<String, Object>of("name", "Updated Org");
        when(http.patch("/organizations/" + testId, body, PolarOrganizationResponse.class)).thenReturn(expected);
        assertThat(organizations.update(testId, body)).isSameAs(expected);
    }
}
