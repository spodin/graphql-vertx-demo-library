package engineer.spodin.library.book;

import graphql.GraphQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryBookRepository implements BookRepository {
    private final Map<Long, Book> books = new ConcurrentHashMap<>();

    InMemoryBookRepository() {
        books.put(1L, new Book(1L, "Code Complete", 1L));
        books.put(2L, new Book(2L, "Thinking in Java", 2L));
        books.put(3L, new Book(3L, "Java Concurrency in Practice", 3L));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public void updateName(Long id, String name) {
        if (books.containsKey(id)) {
            books.get(id).setName(name);
        } else {
            // TODO(spodin): Maybe there is a better way for errors propagation?
            throw new GraphQLException("Book with id=" + id + " not found");
        }
    }
}
