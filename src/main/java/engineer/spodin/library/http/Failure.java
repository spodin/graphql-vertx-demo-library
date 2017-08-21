package engineer.spodin.library.http;

/**
 * Immutable exception that represents occurred failure, optionally enriched with code.
 *
 * <p>Implemented to handle failures in a generic way.
 *
 * <p>By default failure instance holds {@code 500} as getCode,
 * that maps to <a href="https://tools.ietf.org/html/rfc7231#section-6.6.1">Internal Server Error</a>
 * HTTP status.
 */
public final class Failure extends RuntimeException {
    private static final int DEFAULT_CODE = 500; // internal server error
    private static final String DEFAULT_MESSAGE = "No message provided";

    private final int code;

    public Failure(int code) {
        this(code, DEFAULT_MESSAGE, null);
    }

    public Failure(String message) {
        this(DEFAULT_CODE, message, null);
    }

    public Failure(int code, String message) {
        this(code, message, null);
    }

    public Failure(int code, Throwable cause) {
        this(code, DEFAULT_MESSAGE, cause);
    }

    public Failure(String message, Throwable cause) {
        this(DEFAULT_CODE, message, cause);
    }

    public Failure(Throwable cause) {
        this(DEFAULT_CODE, DEFAULT_MESSAGE, cause);
    }

    public Failure(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * Return code associated with this failure.
     *
     * @return error code
     */
    public int getCode() {
        return code;
    }

    /**
     * Wraps specified {@link Throwable} to {@link Failure} instance.
     *
     * <p>If specified one isn't instance of {@link Failure} - than
     * new instance will be constructed using {@code cause.getMessage()}
     * and {@code cause.getCause()} from passed.
     *
     * <p>If specified is {@code null} - new instance will be constructed
     * with default code and message.
     *
     * @param cause non-null cause
     * @return failure instance
     */
    public static Failure wrap(final Throwable cause) {
        if (cause == null) {
            return new Failure(DEFAULT_MESSAGE);
        }
        if (cause instanceof Failure) {
            return (Failure) cause;
        }
        if (cause.getMessage() == null) {
            return new Failure(DEFAULT_MESSAGE, cause.getCause());
        }
        return new Failure(cause.getMessage(), cause.getCause());
    }
}
