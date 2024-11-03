package com.dunky.webapi.vertx_stock_broker.broker;

import com.dunky.webapi.vertx_stock_broker.broker.assets.AssetsRespApi;
import com.dunky.webapi.vertx_stock_broker.broker.quotes.QuotesRestApi;
import com.dunky.webapi.vertx_stock_broker.broker.quotes.QuotesRestApi;
import com.dunky.webapi.vertx_stock_broker.broker.watchlist.WatchListRestApi;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);
  public static final int PORT = 8888;

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.exceptionHandler(error -> {
      LOG.error("Unhandled: ", error);
    });

    vertx.deployVerticle(new MainVerticle(), ar -> {
      if(ar.failed()){
        LOG.error("Failed to deploy: ", ar.cause() );
        return;
      }
      LOG.info("Deployed {}", MainVerticle.class.getName());
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    // Router
    final Router restApi = Router.router(vertx);

    // Route error handling
    restApi.route()
      .handler(BodyHandler.create())
      .failureHandler(handleFailure());

    // HTTP end-point and request handler
    AssetsRespApi.attach(restApi);
    QuotesRestApi.attach(restApi);
    WatchListRestApi.attach(restApi);


    vertx.createHttpServer()
      .requestHandler(restApi)
        .exceptionHandler(error -> LOG.error("HTTP Server error: ", error))
      .listen(PORT).onComplete(http -> {
      if (http.succeeded()) {
        startPromise.complete();
        LOG.info("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

  private Handler<RoutingContext> handleFailure() {
    return errorContext -> {
      if (errorContext.response().ended()) {
        // Ignore completed response
        return;
      }

      LOG.error("Route Error:", errorContext.failure());
      errorContext.response()
        .setStatusCode(500)
        .end(new JsonObject().put("message", "Something went wrong!").encode());
    };
  }
}
