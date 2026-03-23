package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.subscription.PolarSubscriptionResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class SubscriptionsTest {

    private PolarHttpClient http;
    private Subscriptions subscriptions;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        subscriptions = new Subscriptions(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/subscriptions/?customer_id=" + testId + "&active=true&limit=10"), any(TypeReference.class)))
                .thenReturn(expected);
        assertThat(subscriptions.list(testId, true, 10)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarSubscriptionResponse.class);
        when(http.get("/subscriptions/" + testId, PolarSubscriptionResponse.class)).thenReturn(expected);
        assertThat(subscriptions.get(testId)).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarSubscriptionResponse.class);
        var body = Map.<String, Object>of("product_id", "new-prod");
        when(http.patch("/subscriptions/" + testId, body, PolarSubscriptionResponse.class)).thenReturn(expected);
        assertThat(subscriptions.update(testId, body)).isSameAs(expected);
    }

    @Test
    void revokeDelegatesToHttp() {
        subscriptions.revoke(testId);
        verify(http).delete("/subscriptions/" + testId);
    }
}
