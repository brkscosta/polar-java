package sh.polar.sdk;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PolarTest {

    @Test
    void constructorRequiresToken() {
        assertThatThrownBy(() -> new Polar((String) null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Polar(""))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Polar("   "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void resourceAccessorsReturnNonNull() {
        Polar polar = new Polar("test-token");
        assertThat(polar.checkouts()).isNotNull();
        assertThat(polar.checkoutLinks()).isNotNull();
        assertThat(polar.products()).isNotNull();
        assertThat(polar.customers()).isNotNull();
        assertThat(polar.orders()).isNotNull();
        assertThat(polar.subscriptions()).isNotNull();
        assertThat(polar.payments()).isNotNull();
        assertThat(polar.refunds()).isNotNull();
        assertThat(polar.disputes()).isNotNull();
        assertThat(polar.benefits()).isNotNull();
        assertThat(polar.discounts()).isNotNull();
        assertThat(polar.licenseKeys()).isNotNull();
        assertThat(polar.files()).isNotNull();
        assertThat(polar.organizations()).isNotNull();
        assertThat(polar.customFields()).isNotNull();
        assertThat(polar.meters()).isNotNull();
        assertThat(polar.events()).isNotNull();
        assertThat(polar.webhooks()).isNotNull();
        assertThat(polar.metrics()).isNotNull();
        assertThat(polar.customerMeters()).isNotNull();
    }

    @Test
    void resourceAccessorsReturnSameInstance() {
        Polar polar = new Polar("test-token");
        assertThat(polar.customers()).isSameAs(polar.customers());
        assertThat(polar.products()).isSameAs(polar.products());
        assertThat(polar.checkouts()).isSameAs(polar.checkouts());
    }

    @Test
    void builderConfiguresClient() {
        var httpClient = Polar.builder("test-token")
                .baseUrl("https://sandbox-api.polar.sh/v1")
                .connectTimeout(15)
                .maxRetryAttempts(5)
                .retryBackoff(2000)
                .build();
        Polar polar = new Polar(httpClient);
        assertThat(polar.httpClient()).isNotNull();
    }
}
