package com.dunky.vertx.vertx_starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import java.util.UUID;

public class MainVerticle extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);

  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    LOG.debug("Start {}" +getClass().getName());
    vertx.deployVerticle(new VerticleA());
    vertx.deployVerticle(new VerticleB());
    // Deploying multiple instances of VerticleN
    vertx.deployVerticle(VerticleN.class.getName(),
      new DeploymentOptions()
      .setInstances(2)
        .setConfig(new JsonObject()
          .put("id ", UUID.randomUUID().toString())
          .put("name", VerticleN.class.getSimpleName())
        )
    );
    startPromise.complete();
  }
}
