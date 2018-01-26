package engineer.spodin.library;

import com.google.inject.AbstractModule;
import engineer.spodin.library.author.AuthorModule;
import engineer.spodin.library.book.BookModule;
import engineer.spodin.library.graphql.GraphQLModule;
import engineer.spodin.library.http.HttpServer;
import engineer.spodin.library.util.VertxConfig;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Entry point for service startup.
 */
public class ServiceLauncher extends AbstractVerticle {
    private static final Logger log = LoggerFactory.getLogger(ServiceLauncher.class);

    @Override
    public void start(final Future<Void> startup) throws Exception {
        log.info("Starting up...");

        config().put("guice_binder", BootstrapBinder.class.getName());
        DeploymentOptions opts = new DeploymentOptions().setConfig(config());

        deploy(HttpServer.class, opts).setHandler(
                deployment -> {
                    if (deployment.succeeded()) {
                        log.info("Started");
                        startup.complete();
                    } else {
                        log.error(deployment.cause());
                        startup.fail(deployment.cause());
                    }
                }
        );
    }

    private Future<Void> deploy(Class<? extends AbstractVerticle> verticle, DeploymentOptions opts) {
        Future<Void> deployment = Future.future();
        vertx.deployVerticle(
                "java-guice:" + verticle.getName(), opts, ar -> {
                    if (ar.succeeded()) {
                        log.info(verticle.getSimpleName() + " deployed successfully");
                        deployment.complete();
                    } else {
                        deployment.fail(ar.cause());
                    }
                }
        );
        return deployment;
    }

    public static class BootstrapBinder extends AbstractModule {
        @Override
        protected void configure() {
            install(new GraphQLModule());
            install(new AuthorModule());
            install(new BookModule());

            // bind Vert.x configuration
            bind(JsonObject.class)
                    .annotatedWith(VertxConfig.class)
                    .toInstance(Vertx.currentContext().config());
        }
    }
}
