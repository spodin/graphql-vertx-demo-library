package engineer.spodin.library.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import engineer.spodin.library.book.BookRepository;

import javax.inject.Inject;

public class Mutation implements GraphQLMutationResolver {
    private final BookRepository books;

    @Inject
    public Mutation(BookRepository books) {
        this.books = books;
    }

    public boolean changeBookName(Long id, String name) {
        return true;
    }
}
