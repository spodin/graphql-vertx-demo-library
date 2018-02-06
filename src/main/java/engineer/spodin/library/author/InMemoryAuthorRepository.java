package engineer.spodin.library.author;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryAuthorRepository implements AuthorRepository {
    private final Map<Long, Author> authors = new ConcurrentHashMap<>();

    InMemoryAuthorRepository() {
        authors.put(1L, new Author(1L, "Steve McConnell"));
        authors.put(2L, new Author(2L, "Bruce Eckel"));
        authors.put(3L, new Author(3L, "Brian Goetz"));
    }

    @Override
    public Author findById(Long authorId) {
        return authors.get(authorId);
    }
}
