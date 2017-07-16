[![Build Status](https://travis-ci.org/spodin/library.svg?branch=master)](https://travis-ci.org/spodin/library)

Some experiments with wiring up [Vert.x](http://vertx.io), [Guice](https://github.com/google/guice) and [GraphQL](http://graphql.org) together.

**Work in progress**, but all code on `master` branch is stable and can be easily compiled and run using `./gradlew run` execution.

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

### Introspection

Execute your [introspection](http://graphql.org/learn/introspection) query (as described above), 
or get full schema declaration via `GET` request to `/graphql`.