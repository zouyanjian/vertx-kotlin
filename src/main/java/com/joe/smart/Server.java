package com.joe.smart;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;

public class Server extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx);

        router.route().handler(routingContext ->{
            routingContext.response()
                    .putHeader("content-type", "text/html")
                    .end("<!DOCTYPE html><html><body><h1>hello world</h1></body></html>");
        });

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080, result->{
                    if (result.succeeded()) {
                        System.out.println("hello world");
                        startFuture.complete();
                    } else {
                        startFuture.fail(result.cause());
                    }});
    }
}
