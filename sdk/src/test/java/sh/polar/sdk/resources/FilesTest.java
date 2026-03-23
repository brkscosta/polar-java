package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.file.PolarFileResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class FilesTest {

    private PolarHttpClient http;
    private Files files;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        files = new Files(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/files/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(files.list(1, 10)).isSameAs(expected);
    }

    @Test
    void createDelegatesToHttp() {
        var expected = mock(PolarFileResponse.class);
        var body = Map.<String, Object>of("name", "doc.pdf", "mime_type", "application/pdf");
        when(http.post("/files/", body, PolarFileResponse.class)).thenReturn(expected);
        assertThat(files.create(body)).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarFileResponse.class);
        var body = Map.<String, Object>of("name", "updated.pdf");
        when(http.patch("/files/" + testId, body, PolarFileResponse.class)).thenReturn(expected);
        assertThat(files.update(testId, body)).isSameAs(expected);
    }

    @Test
    void deleteDelegatesToHttp() {
        files.delete(testId);
        verify(http).delete("/files/" + testId);
    }

    @Test
    void confirmUploadDelegatesToHttp() {
        var expected = mock(PolarFileResponse.class);
        when(http.post("/files/" + testId + "/uploaded", null, PolarFileResponse.class)).thenReturn(expected);
        assertThat(files.confirmUpload(testId)).isSameAs(expected);
    }
}
