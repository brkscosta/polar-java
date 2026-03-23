package sh.polar.sdk;

import com.cosium.standard_webhooks_consumer.WebhookSignatureVerificationException;
import com.cosium.standard_webhooks_consumer.WebhookSignatureVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * Verifies Polar webhook signatures following the Standard Webhooks specification.
 * <p>
 * Polar encodes the full secret as base64 before passing to StandardWebhook.
 *
 * @see <a href="https://github.com/standard-webhooks/standard-webhooks">Standard Webhooks</a>
 */
public class PolarWebhookVerifier {

    private static final String HEADER_WEBHOOK_ID = "Webhook-Id";
    private static final String HEADER_WEBHOOK_TIMESTAMP = "Webhook-Timestamp";
    private static final String HEADER_WEBHOOK_SIGNATURE = "Webhook-Signature";

    private static final Logger log = LoggerFactory.getLogger(PolarWebhookVerifier.class);

    private final WebhookSignatureVerifier webhookSignatureVerifier;

    /**
     * Creates a new webhook verifier with the given Polar webhook secret.
     * <p>
     * The secret is processed following Polar's convention: the full secret
     * (including any prefix) is base64-encoded, then prefixed with "whsec_"
     * for the Standard Webhooks library.
     *
     * @param webhookSecret the raw webhook secret from Polar dashboard
     */
    public PolarWebhookVerifier(String webhookSecret) {
        // Polar takes the FULL secret (including prefix) and base64 encodes it
        // See: https://github.com/polarsource/polar/blob/main/server/polar/webhook/tasks.py#L88-92
        String b64Secret = Base64.getEncoder().encodeToString(webhookSecret.getBytes(StandardCharsets.UTF_8));
        String standardWebhookSecret = "whsec_" + b64Secret;
        this.webhookSignatureVerifier = WebhookSignatureVerifier.builder(standardWebhookSecret).build();
    }

    /**
     * Verifies a Polar webhook signature.
     *
     * @param payload   the raw webhook payload body
     * @param webhookId the {@code Webhook-Id} header value
     * @param timestamp the {@code Webhook-Timestamp} header value
     * @param signature the {@code Webhook-Signature} header value
     * @return {@code true} if the signature is valid, {@code false} otherwise
     */
    public boolean verify(String payload, String webhookId, String timestamp, String signature) {
        if (payload == null) {
            log.warn("Webhook payload is null");
            return false;
        }
        if (webhookId == null || webhookId.isBlank()) {
            log.warn("Webhook header {} is empty", HEADER_WEBHOOK_ID);
            return false;
        }
        if (timestamp == null || timestamp.isBlank()) {
            log.warn("Webhook header {} is empty", HEADER_WEBHOOK_TIMESTAMP);
            return false;
        }
        if (signature == null || signature.isBlank()) {
            log.warn("Webhook header {} is empty", HEADER_WEBHOOK_SIGNATURE);
            return false;
        }

        try {
            HttpHeaders headers = HttpHeaders.of(
                    Map.of(
                            HEADER_WEBHOOK_ID, List.of(webhookId),
                            HEADER_WEBHOOK_TIMESTAMP, List.of(timestamp),
                            HEADER_WEBHOOK_SIGNATURE, List.of(signature)
                    ),
                    (name, value) -> true
            );

            webhookSignatureVerifier.verify(headers, payload);
            log.debug("Webhook signature verified successfully for webhookId: {}", webhookId);
            return true;
        } catch (WebhookSignatureVerificationException e) {
            log.warn("Webhook signature verification failed for webhookId: {}", webhookId);
            return false;
        }
    }
}
