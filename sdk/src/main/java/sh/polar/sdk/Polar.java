package sh.polar.sdk;

import sh.polar.sdk.http.PolarHttpClient;
import sh.polar.sdk.resources.*;

/**
 * Entry point for the Polar Java SDK.
 * <p>
 * Framework-agnostic client for the Polar payment platform API.
 * Uses {@code java.net.http.HttpClient} under the hood — zero framework dependencies.
 * <p>
 * Usage:
 * <pre>{@code
 * Polar polar = new Polar("pol_token_xxx");
 *
 * // Resource-based API
 * var customers = polar.customers().list(1, 50);
 * var checkout  = polar.checkouts().create(body);
 * var product   = polar.products().get(productId);
 * }</pre>
 * <p>
 * For advanced configuration:
 * <pre>{@code
 * Polar polar = Polar.builder("pol_token_xxx")
 *     .baseUrl("https://sandbox-api.polar.sh/v1")
 *     .connectTimeout(15)
 *     .maxRetryAttempts(5)
 *     .retryBackoff(2000)
 *     .build();
 * }</pre>
 *
 * @see <a href="https://docs.polar.sh/api-reference">Polar API Reference</a>
 */
public class Polar {

    private final PolarHttpClient httpClient;

    // Lazy-initialized resource clients
    private volatile Checkouts checkouts;
    private volatile CheckoutLinks checkoutLinks;
    private volatile Products products;
    private volatile Customers customers;
    private volatile Orders orders;
    private volatile Subscriptions subscriptions;
    private volatile Payments payments;
    private volatile Refunds refunds;
    private volatile Disputes disputes;
    private volatile Benefits benefits;
    private volatile Discounts discounts;
    private volatile LicenseKeys licenseKeys;
    private volatile Files files;
    private volatile Organizations organizations;
    private volatile CustomFields customFields;
    private volatile Meters meters;
    private volatile Events events;
    private volatile Webhooks webhooks;
    private volatile Metrics metrics;
    private volatile CustomerMeters customerMeters;

    /**
     * Creates a new Polar SDK client with default settings.
     *
     * @param accessToken the Polar API access token
     */
    public Polar(String accessToken) {
        this(PolarHttpClient.builder(accessToken).build());
    }

    /**
     * Creates a new Polar SDK client with a pre-configured HTTP client.
     *
     * @param httpClient the configured HTTP client
     */
    public Polar(PolarHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    // ── Resource accessors ────────────────────────

    public Checkouts checkouts() {
        if (checkouts == null) checkouts = new Checkouts(httpClient);
        return checkouts;
    }

    public CheckoutLinks checkoutLinks() {
        if (checkoutLinks == null) checkoutLinks = new CheckoutLinks(httpClient);
        return checkoutLinks;
    }

    public Products products() {
        if (products == null) products = new Products(httpClient);
        return products;
    }

    public Customers customers() {
        if (customers == null) customers = new Customers(httpClient);
        return customers;
    }

    public Orders orders() {
        if (orders == null) orders = new Orders(httpClient);
        return orders;
    }

    public Subscriptions subscriptions() {
        if (subscriptions == null) subscriptions = new Subscriptions(httpClient);
        return subscriptions;
    }

    public Payments payments() {
        if (payments == null) payments = new Payments(httpClient);
        return payments;
    }

    public Refunds refunds() {
        if (refunds == null) refunds = new Refunds(httpClient);
        return refunds;
    }

    public Disputes disputes() {
        if (disputes == null) disputes = new Disputes(httpClient);
        return disputes;
    }

    public Benefits benefits() {
        if (benefits == null) benefits = new Benefits(httpClient);
        return benefits;
    }

    public Discounts discounts() {
        if (discounts == null) discounts = new Discounts(httpClient);
        return discounts;
    }

    public LicenseKeys licenseKeys() {
        if (licenseKeys == null) licenseKeys = new LicenseKeys(httpClient);
        return licenseKeys;
    }

    public Files files() {
        if (files == null) files = new Files(httpClient);
        return files;
    }

    public Organizations organizations() {
        if (organizations == null) organizations = new Organizations(httpClient);
        return organizations;
    }

    public CustomFields customFields() {
        if (customFields == null) customFields = new CustomFields(httpClient);
        return customFields;
    }

    public Meters meters() {
        if (meters == null) meters = new Meters(httpClient);
        return meters;
    }

    public Events events() {
        if (events == null) events = new Events(httpClient);
        return events;
    }

    public Webhooks webhooks() {
        if (webhooks == null) webhooks = new Webhooks(httpClient);
        return webhooks;
    }

    public Metrics metrics() {
        if (metrics == null) metrics = new Metrics(httpClient);
        return metrics;
    }

    public CustomerMeters customerMeters() {
        if (customerMeters == null) customerMeters = new CustomerMeters(httpClient);
        return customerMeters;
    }

    /** Returns the underlying HTTP client for advanced usage. */
    public PolarHttpClient httpClient() {
        return httpClient;
    }

    /** Creates a builder for advanced configuration. */
    public static PolarHttpClient.Builder builder(String accessToken) {
        return PolarHttpClient.builder(accessToken);
    }
}
