package engineer.spodin.library.graphql.web;

import engineer.spodin.library.graphql.util.Queries;
import engineer.spodin.library.http.Failure;
import engineer.spodin.library.http.RouterAwareHandler;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

import static engineer.spodin.library.http.MediaType.APPLICATION_JSON_UTF8;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE;

/**
 * Handles all GraphQL requests.
 */
public class GraphQLHandler implements RouterAwareHandler {
    private static final Logger log = LoggerFactory.getLogger(GraphQLHandler.class);

    private final GraphQL graphQL;

    @Inject
    public GraphQLHandler(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @Override
    public void registerOn(Router router) {
        router.get("/graphql").blockingHandler(this::getSchema);
        router.post("/graphql").blockingHandler(this::handleQuery);
    }

    /**
     * Handles requests for full GraphQL schema declaration.
     *
     * @param ctx routing context
     */
    private void getSchema(final RoutingContext ctx) {
        ExecutionResult result = graphQL.execute(Queries.FULL_INTROSPECTION);
        ctx.response()
           .setStatusCode(OK.code())
           .putHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8)
           .end(Json.encode(new Response(result.getData(), result.getErrors())));
    }

    /**
     * Handles GraphQL Query requests.
     *
     * @param ctx routing context
     */
    private void handleQuery(final RoutingContext ctx) {
        final JsonObject payload = ctx.getBodyAsJson();
        log.debug("Request raw payload: " + payload);

        final Request query = Request.fromJson(payload);
        log.debug("Parsed GraphQL query from payload: " + query);

        try {
            ExecutionInput input = ExecutionInput.newExecutionInput()
                                                 .query(query.query())
                                                 .context(new Object())
                                                 .variables(query.variables())
                                                 .build();

            ExecutionResult result = graphQL.execute(input);

            ctx.response()
               .setStatusCode(OK.code())
               .putHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8)
               .end(Json.encode(new Response(result.getData(), result.getErrors())));

        } catch (GraphQLException e) {
            ctx.fail(Failure.BAD_REQUEST.causedBy(e));
        }
    }

    @Override
    public void handle(final RoutingContext ctx) {
        // NOP, see separate handler methods
    }
}
