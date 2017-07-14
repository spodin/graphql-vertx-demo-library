package engineer.spodin.library.domain.book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
}
