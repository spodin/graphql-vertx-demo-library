package engineer.spodin.library.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import engineer.spodin.library.book.BookRepository;

import javax.inject.Inject;

public class MutationResolver implements GraphQLMutationResolver {
    private final BookRepository books;

    @Inject
    public MutationResolver(BookRepository books) {
        this.books = books;
    }

    public boolean changeBookName(Long id, String name) {
        return true;
    }
}
