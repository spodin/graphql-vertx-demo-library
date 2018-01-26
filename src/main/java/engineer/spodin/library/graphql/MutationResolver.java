package engineer.spodin.library.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import engineer.spodin.library.book.BookRepository;
import graphql.GraphQLException;

import javax.inject.Inject;

/**
 * GraphQL mutations resolver.
 */
public class MutationResolver implements GraphQLMutationResolver {
    private final BookRepository books;

    @Inject
    public MutationResolver(BookRepository books) {
        this.books = books;
    }

    /**
     * Updates book name by specified identifier.
     *
     * @param id   book identifier
     * @param name book new name
     * @return {@code true} if update succeeded
     */
    public boolean changeBookName(Long id, String name) {
        if (name.trim().isEmpty()) {
            throw new GraphQLException("Book name must not be blank");
        }
        books.updateName(id, name);
        return true;
    }
}
