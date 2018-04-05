# Extend vert.x image
FROM vertx/vertx3

ENV VERTICLE_NAME com.joe.smart.HelloWorldVerticle
ENV VERTICLE_FILE build/libs/vertx-kotlin-1.0-SNAPSHOT-fat.jar

ENV JAVA_OPTS "-Dfoo=bar"
ENV VERTX_OPTS "-Dvertx.options.eventLoopPoolSize=26 -Dvertx.options.deployment.worker=true"


# Set the location of the verticles
ENV VERTICLE_HOME /usr/verticles

EXPOSE 8080

COPY $VERTICLE_FILE $VERTICLE_HOME/

#Launch the verticle
WORKDIR $VERTICLE_HOME

ENTRYPOINT ["sh" ,"-c"]

CMD ["exec vertx run $VERTICLE_NAME -cp $VERTICLE_HOME/*"]
