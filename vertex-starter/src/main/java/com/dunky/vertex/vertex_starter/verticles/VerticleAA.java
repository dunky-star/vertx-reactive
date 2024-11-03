package com.dunky.vertex.vertex_starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;



public class VerticleAA extends AbstractVerticle {
  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    System.out.println("Start " +getClass().getName());
    startPromise.complete();
  }

  public void stop(final Promise<Void> stopPromise) throws Exception {
    System.out.println("Stop " +getClass().getName());
    stopPromise.complete();
  }


}
