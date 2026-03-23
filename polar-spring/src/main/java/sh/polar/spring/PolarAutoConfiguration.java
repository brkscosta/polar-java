package sh.polar.spring;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import sh.polar.sdk.Polar;
import sh.polar.sdk.PolarWebhookVerifier;
import sh.polar.sdk.http.PolarHttpClient;

/**
 * Spring Boot autoconfiguration for the Polar SDK.
 * <p>
 * Automatically creates a {@link Polar} bean when {@code polar.access-token}
 * is present, and a {@link PolarWebhookVerifier} when {@code polar.webhook-secret}
 * is configured.
 */
@AutoConfiguration
@EnableConfigurationProperties(PolarProperties.class)
@ConditionalOnProperty(name = "polar.access-token")
public class PolarAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Polar polar(PolarProperties properties) {
        PolarHttpClient httpClient = PolarHttpClient.builder(properties.accessToken())
                .baseUrl(properties.apiUrlOrDefault())
                .connectTimeout(properties.connectTimeoutOrDefault())
                .maxRetryAttempts(properties.maxRetryAttemptsOrDefault())
                .retryBackoff(properties.retryBackoffMillisOrDefault())
                .build();
        return new Polar(httpClient);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "polar.webhook-secret")
    public PolarWebhookVerifier polarWebhookVerifier(PolarProperties properties) {
        return new PolarWebhookVerifier(properties.webhookSecret());
    }
}
