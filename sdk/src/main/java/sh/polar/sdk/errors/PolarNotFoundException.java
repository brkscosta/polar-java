package sh.polar.sdk.errors;

/** Thrown when the API returns HTTP 404 Not Found. */
public class PolarNotFoundException extends PolarApiException {
    public PolarNotFoundException(String message) { super(message, 404); }
}
