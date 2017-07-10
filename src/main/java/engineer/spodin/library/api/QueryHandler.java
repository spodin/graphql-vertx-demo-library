package engineer.spodin.library.api;

import engineer.spodin.library.graphql.web.QueryRequest;
import engineer.spodin.library.graphql.web.QueryResponse;
import engineer.spodin.library.http.RouterAwareHandler;
import graphql.ExecutionResult;
import graphql.GraphQL;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * Handles all GraphQL query requests.
 */
public class QueryHandler implements RouterAwareHandler {
    private final GraphQL graphQL;

    @Inject
    public QueryHandler(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @Override
    public void registerOn(Router router) {
        router.post("/graphql").blockingHandler(this);
    }

    @Override
    public void handle(final RoutingContext ctx) {
        QueryRequest query = QueryRequest.fromJson(ctx.getBodyAsJson());

        ExecutionResult result = graphQL.execute(
                query.query(), new Object(), query.variables());

        ctx.response()
           .setStatusCode(200)
           .putHeader("content-type", "application/json; charset=utf-8")
           .end(Json.encode(new QueryResponse(result.getData(), result.getErrors())));
    }
}
