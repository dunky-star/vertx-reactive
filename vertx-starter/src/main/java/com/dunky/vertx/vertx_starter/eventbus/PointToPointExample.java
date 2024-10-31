package com.dunky.vertx.vertx_starter.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointToPointExample {

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    // Deploy Sender and Receiver Verticles
    vertx.deployVerticle(new Sender());
    vertx.deployVerticle(new Receiver());
  }

  static class Sender extends AbstractVerticle {
    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      // Send a message to "receiver.address" every second
      vertx.setPeriodic(1000, id -> {
        vertx.eventBus().send(Sender.class.getName(), "Sending a message...");
      });

      startPromise.complete();
    }
  }

  static class Receiver extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);
    @Override
    public void start(final Promise<Void> startPromise) throws Exception{
      // Register a handler to listen for messages on "receiver.address"
      vertx.eventBus().<String>consumer(Sender.class.getName(), message -> {
        LOG.debug("Received: {}", message.body());
      });
      startPromise.complete();
    }
  }
}
