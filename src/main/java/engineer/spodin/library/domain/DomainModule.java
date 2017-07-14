package engineer.spodin.library.domain;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import engineer.spodin.library.domain.author.AuthorRepository;
import engineer.spodin.library.domain.author.AuthorRepositoryImpl;
import engineer.spodin.library.domain.book.BookRepository;
import engineer.spodin.library.domain.book.BookRepositoryImpl;

public class DomainModule extends AbstractModule {

    @Provides @Singleton
    public AuthorRepository authorRepository() {
        return new AuthorRepositoryImpl();
    }

    @Provides @Singleton
    public BookRepository bookRepository() {
        return new BookRepositoryImpl();
    }

    @Override
    protected void configure() {
        // NOP
    }
}
