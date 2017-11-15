package engineer.spodin.library.graphql.web;

import graphql.GraphQLError;

import java.util.*;

/**
 * Immutable GraphQL execution result representation
 * ready to be sent over wire.
 */
final class Response implements Map<String, Object> {
    private final Map<String, Object> data;

    Response(Object data, List<GraphQLError> errors) {
        Map<String, Object> result = new LinkedHashMap<>();
        if (!errors.isEmpty()) {
            result.put("errors", errors);
        }
        result.put("data", data);
        this.data = Collections.unmodifiableMap(result);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return data.containsKey(value);
    }

    @Override
    public Object get(Object key) {
        return data.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return data.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return data.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        data.putAll(m);
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public Set<String> keySet() {
        return data.keySet();
    }

    @Override
    public Collection<Object> values() {
        return data.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return data.entrySet();
    }

    @Override
    public String toString() {
        return "Response{" + data.toString() + '}';
    }
}
