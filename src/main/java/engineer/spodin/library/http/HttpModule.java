package engineer.spodin.library.http;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import engineer.spodin.library.api.QueryHandler;
import engineer.spodin.library.api.SchemaHandler;

public class HttpModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<RouterAwareHandler> handlersBinder =
                Multibinder.newSetBinder(binder(), RouterAwareHandler.class);

        handlersBinder.addBinding().to(QueryHandler.class);
        handlersBinder.addBinding().to(SchemaHandler.class);
    }
}
