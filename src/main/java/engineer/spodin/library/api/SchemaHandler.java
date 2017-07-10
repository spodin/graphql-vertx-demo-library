package engineer.spodin.library.api;

import engineer.spodin.library.graphql.util.Queries;
import engineer.spodin.library.graphql.web.QueryResponse;
import engineer.spodin.library.http.RouterAwareHandler;
import graphql.ExecutionResult;
import graphql.GraphQL;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

import static java.util.Collections.emptyMap;

/**
 * Handles requests for full GraphQL declaration.
 */
public class SchemaHandler implements RouterAwareHandler {
    private final GraphQL graphQL;

    @Inject
    public SchemaHandler(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @Override
    public void registerOn(Router router) {
        router.get("/graphql").blockingHandler(this);
    }

    @Override
    public void handle(final RoutingContext ctx) {
        ExecutionResult result = graphQL.execute(
                Queries.INTROSPECTION, new Object(), emptyMap());

        ctx.response()
           .setStatusCode(200)
           .putHeader("content-type", "application/json; charset=utf-8")
           .end(Json.encode(new QueryResponse(result.getData(), result.getErrors())));
    }
}
