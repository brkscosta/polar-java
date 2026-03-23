package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.customfield.PolarCustomFieldResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CustomFieldsTest {

    private PolarHttpClient http;
    private CustomFields customFields;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        customFields = new CustomFields(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/custom-fields/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(customFields.list(1, 10)).isSameAs(expected);
    }

    @Test
    void createDelegatesToHttp() {
        var expected = mock(PolarCustomFieldResponse.class);
        var body = Map.<String, Object>of("slug", "company", "name", "Company", "type", "text");
        when(http.post("/custom-fields/", body, PolarCustomFieldResponse.class)).thenReturn(expected);
        assertThat(customFields.create(body)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarCustomFieldResponse.class);
        when(http.get("/custom-fields/" + testId, PolarCustomFieldResponse.class)).thenReturn(expected);
        assertThat(customFields.get(testId)).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarCustomFieldResponse.class);
        var body = Map.<String, Object>of("name", "Updated Field");
        when(http.patch("/custom-fields/" + testId, body, PolarCustomFieldResponse.class)).thenReturn(expected);
        assertThat(customFields.update(testId, body)).isSameAs(expected);
    }

    @Test
    void deleteDelegatesToHttp() {
        customFields.delete(testId);
        verify(http).delete("/custom-fields/" + testId);
    }
}
