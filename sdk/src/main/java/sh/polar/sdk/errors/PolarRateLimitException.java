package sh.polar.sdk.errors;

/** Thrown when the API returns HTTP 429 Too Many Requests. */
public class PolarRateLimitException extends PolarApiException {
    public PolarRateLimitException(String message) { super(message, 429); }
}
