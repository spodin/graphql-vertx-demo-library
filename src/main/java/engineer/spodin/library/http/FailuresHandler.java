package engineer.spodin.library.http;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

class FailuresHandler implements Handler<RoutingContext> {

    @Override
    public void handle(final RoutingContext failedCtx) {
        Failure failure = Failure.wrap(failedCtx.failure());

        JsonObject body = new JsonObject()
                .put("message", failure.getMessage());

        failedCtx.response()
                 .setStatusCode(failure.getCode())
                 .putHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
                 .end(body.encode());
    }
}
