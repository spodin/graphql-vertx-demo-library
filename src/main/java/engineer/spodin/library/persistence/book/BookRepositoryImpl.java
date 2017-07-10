package engineer.spodin.library.persistence.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookRepositoryImpl implements BookRepository {
    private final Map<Long, Book> books = new ConcurrentHashMap<>();

    public BookRepositoryImpl() {
        books.put(1L, new Book(1L, "Book Name 1", 1L));
        books.put(2L, new Book(2L, "Book Name 2", 2L));
        books.put(3L, new Book(3L, "Book Name 3", 3L));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }
}
