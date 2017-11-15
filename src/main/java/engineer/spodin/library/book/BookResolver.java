package engineer.spodin.library.book;

import com.coxautodev.graphql.tools.GraphQLResolver;
import engineer.spodin.library.author.Author;
import engineer.spodin.library.author.AuthorRepository;
import engineer.spodin.library.book.Book;

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
