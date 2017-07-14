package engineer.spodin.library.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.handler.ResponseTimeHandler;

import javax.inject.Inject;
import java.util.Set;

public class HttpServer extends AbstractVerticle {
    private static final Logger log = LoggerFactory.getLogger(HttpServer.class);

    private static final int PORT = 8080;

    private final Set<RouterAwareHandler> handlers;

    @Inject
    public HttpServer(Set<RouterAwareHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public void start(final Future<Void> serverStartup) throws Exception {
        Router router = Router.router(vertx);
        router.route().handler(LoggerHandler.create(true, LoggerFormat.DEFAULT));
        router.route().handler(BodyHandler.create());
        router.route().handler(ResponseTimeHandler.create());
        router.route().failureHandler(new FailuresHandler());

        handlers.forEach(handler -> handler.registerOn(router));

        vertx.createHttpServer()
             .requestHandler(router::accept)
             .listen(PORT, start -> {
                 if (start.succeeded()) {
                     log.info("HTTP server running on port " + PORT);
                     serverStartup.complete();
                 } else {
                     serverStartup.fail(start.cause());
                 }
             });
    }
}
