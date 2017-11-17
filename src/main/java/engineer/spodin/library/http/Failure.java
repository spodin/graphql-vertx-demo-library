package engineer.spodin.library.http;

/**
 * Immutable exception that represents occurred failure, optionally enriched with code.
 * <p>
 * Implemented to handle failures in a generic way.
 * <p>
 * By default failure instance holds {@code 500} as code,
 * that maps to <a href="https://tools.ietf.org/html/rfc7231#section-6.6.1">Internal Server Error</a>
 * HTTP status.
 */
public final class Failure extends RuntimeException {

    /*
     * Client Errors (4xx)
     */
    public static final Failure BAD_REQUEST = new Failure(400, "BAD REQUEST");
    public static final Failure UNAUTHORIZED = new Failure(401, "UNAUTHORIZED");
    public static final Failure FORBIDDEN = new Failure(403, "FORBIDDEN");
    public static final Failure NOT_FOUND = new Failure(404, "NOT FOUND");
    public static final Failure METHOD_NOT_ALLOWED = new Failure(405, "METHOD NOT ALLOWED");
    public static final Failure CONFLICT = new Failure(409, "CONFLICT");
    public static final Failure UNSUPPORTED_MEDIA_TYPE = new Failure(415, "UNSUPPORTED MEDIA TYPE");
    public static final Failure UNPROCESSABLE_ENTITY = new Failure(422, "UNPROCESSABLE ENTITY");
    public static final Failure TOO_MANY_REQUESTS = new Failure(429, "TOO MANY REQUESTS");

    /*
     * Server Errors (5xx)
     */
    public static final Failure INTERNAL_SERVER_ERROR = new Failure(500, "INTERNAL SERVER ERROR");
    public static final Failure NOT_IMPLEMENTED = new Failure(501, "NOT IMPLEMENTED");
    public static final Failure BAD_GATEWAY = new Failure(502, "BAD GATEWAY");
    public static final Failure SERVICE_UNAVAILABLE = new Failure(503, "SERVICE UNAVAILABLE");
    public static final Failure GATEWAY_TIMEOUT = new Failure(504, "GATEWAY TIMEOUT");

    private static final int DEFAULT_CODE = INTERNAL_SERVER_ERROR.getCode();
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
     * Returns {@link Failure} by specified code. If there is cached instance for specified code - it will be returned,
     * otherwise a new will be created.
     *
     * @param code failure code
     * @return failure instance
     */
    public static Failure valueOf(int code) {
        switch (code) {
            case 400: return BAD_REQUEST;
            case 401: return UNAUTHORIZED;
            case 403: return FORBIDDEN;
            case 404: return NOT_FOUND;
            case 405: return METHOD_NOT_ALLOWED;
            case 409: return CONFLICT;
            case 415: return UNSUPPORTED_MEDIA_TYPE;
            case 422: return UNPROCESSABLE_ENTITY;
            case 429: return TOO_MANY_REQUESTS;
            case 500: return INTERNAL_SERVER_ERROR;
            case 501: return NOT_IMPLEMENTED;
            case 502: return BAD_GATEWAY;
            case 503: return SERVICE_UNAVAILABLE;
            case 504: return GATEWAY_TIMEOUT;
            default:
                return new Failure(code);
        }
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
     * Add cause to this failure.
     * <p>
     * This method invocation doesn't modify original failure, but returns new instance instead.
     *
     * <p>Usage example: Failure.BAD_REQUEST.causedBy(t)
     *
     * @param cause failure cause
     * @return failure instance
     */
    public Failure causedBy(Throwable cause) {
        return new Failure(getCode(), getMessage(), cause);
    }

    /**
     * Add message to this failure.
     *
     * @param message failure message
     * @return failure instance
     */
    public Failure withMessage(String message) {
        return new Failure(getCode(), message, getCause());
    }

    /**
     * Create instance using specified cause.
     * <p>
     * If specified {@link Throwable} is instance of {@link Failure} already - than it will be casted safely,
     * otherwise - new will be created using {@link Throwable#getCause()} and default message.
     * <p>
     * If specified is {@code null} - new instance will be constructed with default code and message.
     *
     * @param cause failure cause
     * @return failure instance
     */
    public static Failure of(final Throwable cause) {
        if (cause == null) {
            return new Failure(DEFAULT_CODE, DEFAULT_MESSAGE);
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
