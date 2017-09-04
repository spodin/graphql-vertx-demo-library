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
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

/**
 * Handles all GraphQL query requests.
 */
public class QueryHandler implements RouterAwareHandler {
    private static final Logger log = LoggerFactory.getLogger(QueryHandler.class);

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
        final JsonObject payload = ctx.getBodyAsJson();
        log.info("Request raw payload: " + payload);

        final Request query = Request.fromJson(ctx.getBodyAsJson());
        log.info("Parsed GraphQL query from payload: " + query);

        try {
            ExecutionResult result = graphQL.execute(query.query(), new Object(), query.variables());
            ctx.response()
               .setStatusCode(OK.code())
               .putHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
               .end(Json.encode(new Response(result.getData(), result.getErrors())));

        } catch (GraphQLException e) {
            ctx.fail(Failure.BAD_REQUEST.causedBy(e));
        }
    }
}
