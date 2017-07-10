[![Build Status](https://travis-ci.org/spodin/library.svg?branch=master)](https://travis-ci.org/spodin/library)

Some experiments with wiring up [Vert.x](http://vertx.io), [Guice](https://github.com/google/guice) and [GraphQL](http://graphql.org) together.

**Work in progress**, but all code on `master` branch is stable and can be easily compiled and run using `./gradlew run` execution.

### Demo Request

Send `POST` to `/graphql` with JSON body:

```json
{
	"query":"{books{id,name,author{id,name}}}",
	"variables":{}
}
```