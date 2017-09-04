package engineer.spodin.library.http;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * Failures handler.
 */
class FailuresHandler implements Handler<RoutingContext> {

    @Override
    public final void handle(final RoutingContext ctx) {
        int code = ctx.statusCode();
        Failure failure = (code == -1) ? Failure.of(ctx.failure()) : new Failure(code);

        ctx.response()
           .setStatusCode(failure.getCode())
           .putHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8)
           .end(responseBody(failure).encode());
    }

    /**
     * Maps {@link Failure} to response body.
     *
     * <p>Must return <b>non</b> {@code null} {@link JsonObject}.
     *
     * @param failure failure instance
     * @return response body
     */
    protected JsonObject responseBody(Failure failure) {
        return new JsonObject()
                .put("timestamp", System.currentTimeMillis())
                .put("code", failure.getCode())
                .put("message", failure.getMessage())
                .put("cause", (failure.getCause() == null) ? "" : failure.getCause().getMessage());
    }
}
