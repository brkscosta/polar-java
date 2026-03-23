# Polar Java SDK

Unofficial Java SDK for the [Polar](https://polar.sh) payment platform API.

Framework-agnostic — uses `java.net.http.HttpClient` (Java 11+) with zero framework dependencies.

## Modules

| Module | Description |
|---|---|
| `polar-java-sdk` | Core SDK — works with any Java framework |
| `polar-spring` | Optional Spring Boot auto-configuration |

## Quick Start

### Pure Java (no framework)

```java
import sh.polar.sdk.Polar;

Polar polar = new Polar("pol_token_xxx");

// List customers
var customers = polar.customers().list(1, 50);

// Create a checkout
var checkout = polar.checkouts().create(Map.of(
    "products", List.of("product_id"),
    "success_url", "https://example.com/success",
    "customer_email", "user@example.com"
));

// Get a product
var product = polar.products().get(productId);
```

### Advanced Configuration

```java
Polar polar = new Polar(
    Polar.builder("pol_token_xxx")
        .baseUrl("https://sandbox-api.polar.sh/v1")  // Sandbox
        .connectTimeout(15)
        .maxRetryAttempts(5)
        .retryBackoff(2000)
        .build()
);
```

### Spring Boot

Add `polar-spring` to your dependencies:

```yaml
# application.yml
polar:
  access-token: ${POLAR_ACCESS_TOKEN}
  base-url: https://api.polar.sh/v1  # optional, this is the default
```

```java
@Autowired
private Polar polar;

public void example() {
    var products = polar.products().list(50);
}
```

## Resources

All 20 Polar API resource categories are supported:

| Resource | Methods |
|---|---|
| `checkouts()` | create, list, get, update |
| `checkoutLinks()` | create, list, get, update, delete |
| `products()` | create, list, get, update, updateBenefits |
| `customers()` | create, list, get, update, delete, getState, findByEmail, getByExternalId, createSession |
| `orders()` | list, get, update, getInvoice, generateInvoice |
| `subscriptions()` | list, get, update, revoke |
| `payments()` | list, get |
| `refunds()` | list, create |
| `disputes()` | list, get |
| `benefits()` | create, list, get, update, delete, listGrants, listGrantsByBenefit |
| `discounts()` | create, list, get, update, delete |
| `licenseKeys()` | list, get, update, activate, deactivate, validate |
| `files()` | create, list, update, delete, confirmUpload |
| `organizations()` | create, list, get, update |
| `customFields()` | create, list, get, update, delete |
| `meters()` | create, list, get, update, getQuantities |
| `events()` | list, get, ingest, listNames |
| `webhooks()` | createEndpoint, listEndpoints, getEndpoint, updateEndpoint, deleteEndpoint, listDeliveries, redeliverEvent |
| `metrics()` | get, getLimits |
| `customerMeters()` | list, get |

## Webhook Verification

```java
import sh.polar.sdk.PolarWebhookVerifier;

boolean valid = PolarWebhookVerifier.verify(
    requestBody,     // raw request body string
    headers,         // Map<String, String> with webhook-id, webhook-timestamp, webhook-signature
    webhookSecret    // your webhook secret
);
```

## Error Handling

The SDK throws typed exceptions based on HTTP status codes:

| Exception | HTTP Status | Description |
|---|---|---|
| `PolarAuthenticationException` | 401 | Invalid or expired token |
| `PolarNotFoundException` | 404 | Resource not found |
| `PolarValidationException` | 422 | Request validation failed |
| `PolarRateLimitException` | 429 | Rate limit exceeded |
| `PolarApiException` | 5xx / other | Server error or unexpected status |

```java
try {
    polar.customers().get(customerId);
} catch (PolarNotFoundException e) {
    // Handle not found
} catch (PolarRateLimitException e) {
    // Handle rate limit - built-in retry handles most cases
} catch (PolarApiException e) {
    // Handle other errors
    System.out.println("Status: " + e.getStatusCode());
}
```

## Built-in Features

- **Retry with exponential backoff** — configurable max attempts and backoff duration
- **Respects `Retry-After` header** — for 429 rate limit responses
- **Error mapping** — HTTP status codes mapped to typed exceptions
- **Jackson JSON** — with `JavaTimeModule` for `Instant`/`OffsetDateTime` support
- **SLF4J logging** — debug-level request/response logging

## Requirements

- Java 11+
- No framework dependencies (core SDK)
- Spring Boot 3.4+ (optional starter)

## License

MIT
