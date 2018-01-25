package engineer.spodin.library.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import engineer.spodin.library.book.BookRepository;
import graphql.GraphQLException;

import javax.inject.Inject;

public class MutationResolver implements GraphQLMutationResolver {
    private final BookRepository books;

    @Inject
    public MutationResolver(BookRepository books) {
        this.books = books;
    }

    public boolean changeBookName(Long id, String name) {
        if (name.trim().isEmpty()) {
            throw new GraphQLException("Book name must not be blank");
        }
        books.updateName(id, name);
        return true;
    }
}
