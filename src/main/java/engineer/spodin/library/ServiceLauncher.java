package engineer.spodin.library;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import engineer.spodin.library.http.HttpModule;
import engineer.spodin.library.http.HttpServer;
import engineer.spodin.library.graphql.GraphQLModule;
import engineer.spodin.library.domain.DomainModule;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class ServiceLauncher extends AbstractVerticle {

    @Override
    public void start(final Future<Void> startup) throws Exception {
        config().put("guice_binder", BootstrapBinder.class.getName());

        DeploymentOptions opts = new DeploymentOptions().setConfig(config());

        deploy(HttpServer.class, opts).setHandler(
                deployment -> {
                    if (deployment.succeeded()) {
                        startup.complete();
                    } else {
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
                        deployment.succeeded();
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
            install(new HttpModule());
            install(new GraphQLModule());
            install(new DomainModule());

            // bind Vert.x configuration
            bind(JsonObject.class)
                    .annotatedWith(Names.named("config"))
                    .toInstance(Vertx.currentContext().config());
        }
    }
}
