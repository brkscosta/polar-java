package sh.polar.sdk.errors;

/**
 * Base exception thrown when a Polar API call fails.
 * <p>
 * Subclasses provide more specific error information based on HTTP status codes:
 * <ul>
 *   <li>{@link PolarAuthenticationException} — 401 Unauthorized</li>
 *   <li>{@link PolarNotFoundException} — 404 Not Found</li>
 *   <li>{@link PolarValidationException} — 422 Unprocessable Entity</li>
 *   <li>{@link PolarRateLimitException} — 429 Too Many Requests</li>
 * </ul>
 */
public class PolarApiException extends RuntimeException {

    private final int statusCode;

    public PolarApiException(String message) {
        super(message);
        this.statusCode = 0;
    }

    public PolarApiException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = 0;
    }

    public PolarApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public PolarApiException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    /** Returns the HTTP status code from the API response, or {@code 0} if not available. */
    public int getStatusCode() {
        return statusCode;
    }
}
