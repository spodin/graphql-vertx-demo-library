package engineer.spodin.library.http;

/**
 * Represents occurred failure optionally enriched with getCode.
 *
 * <p>Implemented to handle failures in a generic way.
 *
 * <p>By default failure instance holds {@code 500} as getCode,
 * that maps to <a href="https://tools.ietf.org/html/rfc7231#section-6.6.1">Internal Server Error</a>
 * HTTP status.
 */
public class Failure extends RuntimeException {
    private static final int DEFAULT_CODE = 500; // internal server error

    private int code = DEFAULT_CODE;

    public Failure(String message) {
        super(message);
    }

    public Failure(String message, Throwable cause) {
        super(message, cause);
    }

    public Failure(Throwable cause) {
        super(cause);
    }

    public Failure(int code, String message) {
        super(message);
        setCode(code);
    }

    public Failure(int code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }

    public Failure(int code, Throwable cause) {
        super(cause);
        setCode(code);
    }

    /**
     * Return code associated with this failure.
     *
     * @return error code
     */
    public int getCode() {
        return code;
    }

    private void setCode(int code) {
        this.code = code;
    }

    /**
     * Wraps specified {@link Throwable} to failure instance.
     *
     * <p>If specified one isn't instance of {@link Failure} - than
     * new instance will be constructed using {@code cause.getMessage()}
     * and {@code cause.getCause()} from passed.
     *
     * @param cause non-null cause
     * @return failure instance
     */
    public static Failure wrap(final Throwable cause) {
        if (cause instanceof Failure) {
            return (Failure) cause;
        }
        if (cause.getMessage() == null) {
            return new Failure("No message provided", cause.getCause());
        }
        return new Failure(cause.getMessage(), cause.getCause());
    }
}
