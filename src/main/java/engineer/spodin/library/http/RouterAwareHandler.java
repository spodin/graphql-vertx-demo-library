package engineer.spodin.library.http;

import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Self-registering handler interface.
 */
public interface RouterAwareHandler extends Handler<RoutingContext> {

    /**
     * Registers itself on passed {@link Router} instance.
     *
     * @param router router
     */
    void registerOn(Router router);
}
