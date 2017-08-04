package engineer.spodin.library.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import engineer.spodin.library.graphql.resolvers.BookResolver;
import engineer.spodin.library.graphql.resolvers.Query;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

import java.util.ArrayList;
import java.util.Set;

public class GraphQLModule extends AbstractModule {

    private static final String[] SCHEMA_FILES = {
            "graphql/root.graphql",
            "graphql/domain.graphql"
    };

    @Override
    protected void configure() {
        Multibinder<GraphQLResolver<?>> resolversBinder =
                Multibinder.newSetBinder(binder(), new TypeLiteral<GraphQLResolver<?>>() { });

        resolversBinder.addBinding().to(BookResolver.class);
        resolversBinder.addBinding().to(Query.class);
    }

    @Provides @Singleton
    GraphQLSchema graphQLSchema(Set<GraphQLResolver<?>> resolvers) {
        return SchemaParser
                .newParser()
                .files(SCHEMA_FILES)
                .resolvers(new ArrayList<>(resolvers))
                .build()
                .makeExecutableSchema();
    }

    @Provides @Singleton
    public GraphQL graphQL(GraphQLSchema schema) {
        return GraphQL.newGraphQL(schema).build();
    }
}
