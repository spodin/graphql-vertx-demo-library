package engineer.spodin.library.book;

import com.coxautodev.graphql.tools.GraphQLResolver;
import engineer.spodin.library.author.Author;
import engineer.spodin.library.author.AuthorRepository;

import javax.inject.Inject;

/**
 * GraphQL {@link Book} fields resolver.
 */
public class BookResolver implements GraphQLResolver<Book> {
    private final AuthorRepository authors;

    @Inject
    public BookResolver(AuthorRepository authors) {
        this.authors = authors;
    }

    /**
     * Returns author by specified book.
     *
     * @param book book entity
     * @return author of the book
     */
    public Author author(Book book) {
        return authors.findById(book.getAuthorId());
    }
}
