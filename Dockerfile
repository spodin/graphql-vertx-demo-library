FROM openjdk:8-jre-alpine

ENV VERTICLE_HOME=/usr/verticles \
    JAVA_OPTS="-Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.Log4j2LogDelegateFactory"

COPY src/main/resources/conf.json $VERTICLE_HOME/
COPY build/libs/app.jar $VERTICLE_HOME/

WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java $JAVA_OPTS -jar app.jar -conf ./conf.json"]

EXPOSE 8080