package engineer.spodin.library.http;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

class FailuresHandler implements Handler<RoutingContext> {

    @Override
    public void handle(final RoutingContext failedCtx) {
        JsonObject body = new JsonObject()
                .put("message", failedCtx.failure().getMessage());

        failedCtx.response()
                 .setStatusCode(HttpResponseStatus.BAD_REQUEST.code())
                 .putHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
                 .end(body.encode());
    }
}
