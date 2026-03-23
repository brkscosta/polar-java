package sh.polar.sdk.errors;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PolarExceptionsTest {

    @Test
    void apiExceptionWithMessage() {
        var ex = new PolarApiException("something failed");
        assertThat(ex.getMessage()).isEqualTo("something failed");
        assertThat(ex.getStatusCode()).isEqualTo(0);
    }

    @Test
    void apiExceptionWithCause() {
        var cause = new RuntimeException("root cause");
        var ex = new PolarApiException("failed", cause);
        assertThat(ex.getCause()).isSameAs(cause);
        assertThat(ex.getStatusCode()).isEqualTo(0);
    }

    @Test
    void apiExceptionWithStatusCode() {
        var ex = new PolarApiException("server error", 500);
        assertThat(ex.getStatusCode()).isEqualTo(500);
    }

    @Test
    void apiExceptionWithStatusCodeAndCause() {
        var cause = new RuntimeException("cause");
        var ex = new PolarApiException("error", 503, cause);
        assertThat(ex.getStatusCode()).isEqualTo(503);
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Test
    void authenticationExceptionExtendsPolarApiException() {
        var ex = new PolarAuthenticationException("unauthorized");
        assertThat(ex).isInstanceOf(PolarApiException.class);
        assertThat(ex.getMessage()).isEqualTo("unauthorized");
    }

    @Test
    void notFoundExceptionExtendsPolarApiException() {
        var ex = new PolarNotFoundException("not found");
        assertThat(ex).isInstanceOf(PolarApiException.class);
        assertThat(ex.getMessage()).isEqualTo("not found");
    }

    @Test
    void validationExceptionExtendsPolarApiException() {
        var ex = new PolarValidationException("validation error");
        assertThat(ex).isInstanceOf(PolarApiException.class);
        assertThat(ex.getMessage()).isEqualTo("validation error");
    }

    @Test
    void rateLimitExceptionExtendsPolarApiException() {
        var ex = new PolarRateLimitException("rate limited");
        assertThat(ex).isInstanceOf(PolarApiException.class);
        assertThat(ex.getMessage()).isEqualTo("rate limited");
    }

    @Test
    void allExceptionsAreRuntimeExceptions() {
        assertThat(new PolarApiException("test")).isInstanceOf(RuntimeException.class);
        assertThat(new PolarAuthenticationException("test")).isInstanceOf(RuntimeException.class);
        assertThat(new PolarNotFoundException("test")).isInstanceOf(RuntimeException.class);
        assertThat(new PolarValidationException("test")).isInstanceOf(RuntimeException.class);
        assertThat(new PolarRateLimitException("test")).isInstanceOf(RuntimeException.class);
    }
}
