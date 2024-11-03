package com.dunky.webapi.vertx_stock_broker.broker.assets;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssetsRespApi {
  private static final Logger LOG = LoggerFactory.getLogger(AssetsRespApi.class);

  public static void attach(Router parent){
    parent.get("/assets").handler(context -> {
      final JsonArray response = new JsonArray();
      response
        .add(new Asset("AAPL"))
        .add(new Asset( "AMZN"))
        .add(new Asset( "NFLX"))
        .add(new Asset( "TSLA"));

      LOG.info("Path {} responds with {}", context.normalizedPath(), response.encode());
      context.response().end(response.toBuffer());
    });
  }

}