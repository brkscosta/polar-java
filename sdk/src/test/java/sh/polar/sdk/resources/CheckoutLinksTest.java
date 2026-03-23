package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.checkout.PolarCheckoutLinkResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CheckoutLinksTest {

    private PolarHttpClient http;
    private CheckoutLinks checkoutLinks;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        checkoutLinks = new CheckoutLinks(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/checkout-links/?page=1&limit=10"), any(TypeReference.class))).thenReturn(expected);
        assertThat(checkoutLinks.list(1, 10)).isSameAs(expected);
    }

    @Test
    void createDelegatesToHttp() {
        var expected = mock(PolarCheckoutLinkResponse.class);
        var body = Map.<String, Object>of("product_id", "prod_1");
        when(http.post("/checkout-links/", body, PolarCheckoutLinkResponse.class)).thenReturn(expected);
        assertThat(checkoutLinks.create(body)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarCheckoutLinkResponse.class);
        when(http.get("/checkout-links/" + testId, PolarCheckoutLinkResponse.class)).thenReturn(expected);
        assertThat(checkoutLinks.get(testId)).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarCheckoutLinkResponse.class);
        var body = Map.<String, Object>of("label", "Updated");
        when(http.patch("/checkout-links/" + testId, body, PolarCheckoutLinkResponse.class)).thenReturn(expected);
        assertThat(checkoutLinks.update(testId, body)).isSameAs(expected);
    }

    @Test
    void deleteDelegatesToHttp() {
        checkoutLinks.delete(testId);
        verify(http).delete("/checkout-links/" + testId);
    }
}
