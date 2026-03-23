package sh.polar.sdk.errors;

/** Thrown when the API returns HTTP 401 Unauthorized. */
public class PolarAuthenticationException extends PolarApiException {
    public PolarAuthenticationException(String message) { super(message, 401); }
}
