package sh.polar.sdk.models.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Represents an order invoice response from Polar API.
 * Used for GET /orders/{id}/invoice endpoint response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarInvoiceResponse(
        @JsonProperty("url") String url
) {
}
