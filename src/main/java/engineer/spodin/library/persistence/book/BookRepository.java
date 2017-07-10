package engineer.spodin.library.persistence.book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
}
