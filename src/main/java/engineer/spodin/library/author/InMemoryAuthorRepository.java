package engineer.spodin.library.author;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryAuthorRepository implements AuthorRepository {
    private final Map<Long, Author> authors = new ConcurrentHashMap<>();

    InMemoryAuthorRepository() {
        authors.put(1L, new Author(1L, "Author 1"));
        authors.put(2L, new Author(2L, "Author 2"));
        authors.put(3L, new Author(3L, "Author 3"));
    }

    @Override
    public Author findById(Long authorId) {
        return authors.get(authorId);
    }
}
