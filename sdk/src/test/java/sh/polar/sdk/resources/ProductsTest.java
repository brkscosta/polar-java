package sh.polar.sdk.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.models.common.PolarListResponse;
import sh.polar.sdk.models.product.PolarProductResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ProductsTest {

    private PolarHttpClient http;
    private Products products;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        http = mock(PolarHttpClient.class);
        products = new Products(http);
    }

    @Test
    void listDelegatesToHttp() {
        var expected = mock(PolarListResponse.class);
        when(http.get(eq("/products/?limit=25"), any(TypeReference.class)))
                .thenReturn(expected);
        assertThat(products.list(25)).isSameAs(expected);
    }

    @Test
    void getDelegatesToHttp() {
        var expected = mock(PolarProductResponse.class);
        when(http.get("/products/" + testId, PolarProductResponse.class))
                .thenReturn(expected);
        assertThat(products.get(testId)).isSameAs(expected);
    }

    @Test
    void createDelegatesToHttp() {
        var expected = mock(PolarProductResponse.class);
        var body = Map.<String, Object>of("name", "Test Product");
        when(http.post("/products/", body, PolarProductResponse.class))
                .thenReturn(expected);
        assertThat(products.create(body)).isSameAs(expected);
    }

    @Test
    void updateDelegatesToHttp() {
        var expected = mock(PolarProductResponse.class);
        var body = Map.<String, Object>of("name", "Updated");
        when(http.patch("/products/" + testId, body, PolarProductResponse.class))
                .thenReturn(expected);
        assertThat(products.update(testId, body)).isSameAs(expected);
    }

    @Test
    void updateBenefitsDelegatesToHttp() {
        var expected = mock(PolarProductResponse.class);
        var body = Map.<String, Object>of("benefits", List.of("benefit-1"));
        when(http.post("/products/" + testId + "/benefits", body, PolarProductResponse.class))
                .thenReturn(expected);
        assertThat(products.updateBenefits(testId, body)).isSameAs(expected);
    }
}
