package engineer.spodin.library.graphql.web;

import graphql.GraphQLError;

import java.util.*;

/**
 * Immutable GraphQL execution result representation
 * ready to be sent over wire.
 */
final class Response implements Map<String, Object> {
    private final Map<String, Object> body;

    Response(Object data, List<GraphQLError> errors) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("data", data);
        if (!errors.isEmpty()) {
            result.put("errors", errors);
        }
        this.body = Collections.unmodifiableMap(result);
    }

    @Override
    public int size() {
        return body.size();
    }

    @Override
    public boolean isEmpty() {
        return body.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return body.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return body.containsKey(value);
    }

    @Override
    public Object get(Object key) {
        return body.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return body.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return body.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        body.putAll(m);
    }

    @Override
    public void clear() {
        body.clear();
    }

    @Override
    public Set<String> keySet() {
        return body.keySet();
    }

    @Override
    public Collection<Object> values() {
        return body.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return body.entrySet();
    }

    @Override
    public String toString() {
        return "Response{" + body.toString() + '}';
    }
}
