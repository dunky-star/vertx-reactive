package com.dunky.webapi.vertx_stock_broker.broker;

import com.dunky.webapi.vertx_stock_broker.broker.assets.AssetsRespApi;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.exceptionHandler(error -> {
      LOG.error("Unhandled: ", error);
    });

    vertx.deployVerticle(new MainVerticle(), ar -> {
      if(ar.failed()){
        LOG.error("Falied to deploy: ", ar.cause() );
        return;
      }
      LOG.info("Deployed {}", MainVerticle.class.getName());
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    // Router
    final Router restApi = Router.router(vertx);

    // HTTP end-point and request handler
    AssetsRespApi.attach(restApi);


    vertx.createHttpServer()
      .requestHandler(restApi)
        .exceptionHandler(error -> LOG.error("HTTP Server error: ", error))
      .listen(8888).onComplete(http -> {
      if (http.succeeded()) {
        startPromise.complete();
        LOG.info("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
