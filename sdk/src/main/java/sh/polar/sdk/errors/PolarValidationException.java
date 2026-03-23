package sh.polar.sdk.errors;

/** Thrown when the API returns HTTP 422 Unprocessable Entity. */
public class PolarValidationException extends PolarApiException {
    public PolarValidationException(String message) { super(message, 422); }
}
