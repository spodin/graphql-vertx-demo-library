package engineer.spodin.library.persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import engineer.spodin.library.persistence.author.AuthorRepository;
import engineer.spodin.library.persistence.author.AuthorRepositoryImpl;
import engineer.spodin.library.persistence.book.BookRepository;
import engineer.spodin.library.persistence.book.BookRepositoryImpl;

public class PersistenceModule extends AbstractModule {

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
