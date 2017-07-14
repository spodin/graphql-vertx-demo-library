package engineer.spodin.library.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import engineer.spodin.library.domain.author.Author;
import engineer.spodin.library.domain.author.AuthorRepository;
import engineer.spodin.library.domain.book.Book;

import javax.inject.Inject;

public class BookResolver implements GraphQLResolver<Book> {
    private final AuthorRepository authors;

    @Inject
    public BookResolver(AuthorRepository authors) {
        this.authors = authors;
    }

    public Author author(Book book) {
        return authors.findById(book.getAuthorId());
    }
}
