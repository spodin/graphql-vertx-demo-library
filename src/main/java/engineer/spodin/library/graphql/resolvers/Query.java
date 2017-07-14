package engineer.spodin.library.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import engineer.spodin.library.domain.book.Book;
import engineer.spodin.library.domain.book.BookRepository;

import javax.inject.Inject;
import java.util.List;

public class Query implements GraphQLRootResolver {
    private final BookRepository books;

    @Inject
    public Query(BookRepository books) {
        this.books = books;
    }

    public List<Book> books() {
        return books.findAll();
    }
}
