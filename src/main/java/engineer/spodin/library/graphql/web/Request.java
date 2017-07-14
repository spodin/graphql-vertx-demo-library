package engineer.spodin.library.graphql.web;

import io.vertx.core.json.JsonObject;

import java.util.Map;

import static java.util.Collections.emptyMap;

/**
 * Immutable GraphQL query request representation.
 */
public final class Request {
    private final String query;
    private final Map<String, Object> variables;

    private Request(String query, Map<String, Object> variables) {
        this.query = query;
        this.variables = variables;
    }

    /**
     * Parses query request body.
     *
     * @param json json body object
     * @return parsed request query instance
     */
    public static Request fromJson(JsonObject json) {
        return new Request(
                json.getString("query"),
                parseMap(json.getJsonObject("variables"))
        );
    }

    private static Map<String, Object> parseMap(JsonObject json) {
        return json == null ? emptyMap() : json.getMap();
    }

    /**
     * Returns query string.
     *
     * @return query string
     */
    public String query() {
        return query;
    }

    /**
     * Returns specified query variables as unmodifiable
     * map instance.
     *
     * @return variables map
     */
    public Map<String, Object> variables() {
        return variables;
    }

    @Override
    public String toString() {
        return "Request{" +
                "query='" + query + '\'' +
                ", variables=" + variables +
                '}';
    }
}
