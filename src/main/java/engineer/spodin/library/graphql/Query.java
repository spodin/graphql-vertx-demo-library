package engineer.spodin.library.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import engineer.spodin.library.book.Book;
import engineer.spodin.library.book.BookRepository;

import javax.inject.Inject;
import java.util.List;

public class Query implements GraphQLQueryResolver {
    private final BookRepository books;

    @Inject
    public Query(BookRepository books) {
        this.books = books;
    }

    public List<Book> books() {
        return books.findAll();
    }
}