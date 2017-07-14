package engineer.spodin.library.query;

import engineer.spodin.library.graphql.web.Request;
import engineer.spodin.library.graphql.web.Response;
import engineer.spodin.library.http.RouterAwareHandler;
import graphql.ExecutionResult;
import graphql.GraphQL;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.http.HttpHeaders;
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
        Request query = Request.fromJson(ctx.getBodyAsJson());

        ExecutionResult result = graphQL.execute(
                query.query(), new Object(), query.variables());

        ctx.response()
           .setStatusCode(HttpResponseStatus.OK.code())
           .putHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
           .end(Json.encode(new Response(result.getData(), result.getErrors())));
    }
}
