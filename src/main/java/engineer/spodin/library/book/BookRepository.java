package engineer.spodin.library.book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
}
