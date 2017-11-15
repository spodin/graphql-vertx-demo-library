package engineer.spodin.library.graphql.web;

import graphql.GraphQLError;

import java.util.*;

/**
 * Immutable GraphQL execution result representation
 * ready to be sent over wire.
 */
final class Response implements Map<String, Object> {
    private final Map<String, Object> result;

    Response(Object data, List<GraphQLError> errors) {
        Map<String, Object> result = new LinkedHashMap<>();
        if (!errors.isEmpty()) {
            result.put("errors", errors);
        }
        result.put("data", data);
        this.result = Collections.unmodifiableMap(result);
    }

    @Override
    public int size() {
        return result.size();
    }

    @Override
    public boolean isEmpty() {
        return result.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return result.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return result.containsKey(value);
    }

    @Override
    public Object get(Object key) {
        return result.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return result.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return result.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        result.putAll(m);
    }

    @Override
    public void clear() {
        result.clear();
    }

    @Override
    public Set<String> keySet() {
        return result.keySet();
    }

    @Override
    public Collection<Object> values() {
        return result.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return result.entrySet();
    }

    @Override
    public String toString() {
        return "Response{" + result.toString() + '}';
    }
}
