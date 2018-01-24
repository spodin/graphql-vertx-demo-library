[![Build Status](https://travis-ci.org/spodin/library.svg?branch=master)](https://travis-ci.org/spodin/library)

Some experiments with wiring up [Vert.x](http://vertx.io), [Guice](https://github.com/google/guice) and [GraphQL](http://graphql.org) together.

## Build and Run

1. Execute: `./gradlew vertxRun`
2. Point your browser to: [`http://localhost:8080/graphql`](http://localhost:8080/graphql)

## Discovering the GraphQL API

### Queries Execution

Send `POST` to `/graphql` with JSON body.

Example:

```json
{
	"query":"{books{id,name,author{id,name}}}",
	"variables":{}
}
```

Currently, only one query per call is supported due to [limitations of GraphQL Java implementation](https://github.com/graphql-java/graphql-java/issues/431).

### Introspection

Execute your [introspection](http://graphql.org/learn/introspection) query (as described above), 
or get full schema declaration via `GET` request to `/graphql`.

## Developer Tools

- [GraphiQL](https://github.com/graphql/graphiql) (self-hosted, [demo](http://graphql.org/swapi-graphql/))
- [Insomnia](https://insomnia.rest/) (standalone REST client)