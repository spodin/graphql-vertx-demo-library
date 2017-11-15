package engineer.spodin.library.author;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class AuthorModule extends AbstractModule {

    @Provides
    @Singleton
    public AuthorRepository authorRepository() {
        return new InMemoryAuthorRepository();
    }

    @Override
    protected void configure() {
        // NOP
    }
}
