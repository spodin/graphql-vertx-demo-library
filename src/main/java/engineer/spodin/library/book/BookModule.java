package engineer.spodin.library.book;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class BookModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<GraphQLResolver<?>> resolversBinder =
                Multibinder.newSetBinder(binder(), new TypeLiteral<GraphQLResolver<?>>() { });

        resolversBinder.addBinding().to(BookResolver.class);
    }

    @Provides
    @Singleton
    public BookRepository bookRepository() {
        return new InMemoryBookRepository();
    }
}
