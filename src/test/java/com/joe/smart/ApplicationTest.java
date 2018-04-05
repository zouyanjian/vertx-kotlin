package com.joe.smart;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class ApplicationTest  {

    private Vertx vertx;

    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();

        vertx.deployVerticle(HelloWorldVerticle.class.getName(), testContext.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test
    public void testApplication(TestContext context) {
        Async async = context.async();
        HttpClient client = vertx.createHttpClient();
        client.getNow(8080, "localhost", "/", res -> {
            res.handler( body -> {

                context.assertTrue(body.toString().contains("hello"));
                client.close();
                async.complete();
            });
        });
    }
}
