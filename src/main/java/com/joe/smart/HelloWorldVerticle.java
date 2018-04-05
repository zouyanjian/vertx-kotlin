package com.joe.smart;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class HelloWorldVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.createHttpServer().requestHandler( req->{

            req.response().end("hello world"+Thread.currentThread().getName());

        }).listen(8080, result->{
            if (result.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(result.cause());
            }
        });
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        super.stop(stopFuture);
    }
}
