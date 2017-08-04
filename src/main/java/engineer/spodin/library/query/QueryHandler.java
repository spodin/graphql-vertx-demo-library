package engineer.spodin.library.query;

import engineer.spodin.library.graphql.web.Request;
import engineer.spodin.library.graphql.web.Response;
import engineer.spodin.library.http.Failure;
import engineer.spodin.library.http.RouterAwareHandler;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLException;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

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
        final Request query = Request.fromJson(ctx.getBodyAsJson());
        try {
            ExecutionResult result = graphQL.execute(query.query(), new Object(), query.variables());
            ctx.response()
               .setStatusCode(OK.code())
               .putHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
               .end(Json.encode(new Response(result.getData(), result.getErrors())));

        } catch (GraphQLException e) {
            ctx.fail(new Failure(BAD_REQUEST.code(), e));
        }
    }
}
