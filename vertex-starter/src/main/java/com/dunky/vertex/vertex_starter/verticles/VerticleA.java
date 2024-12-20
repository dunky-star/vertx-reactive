package com.dunky.vertex.vertex_starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleA extends AbstractVerticle {
  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    System.out.println("Start " +getClass().getName());
    vertx.deployVerticle(new VerticleAA(), whenDeployed->{
      System.out.println("Deployed " + VerticleAA.class.getName());
      vertx.undeploy(whenDeployed.result());
    });
    vertx.deployVerticle(new VerticleAB(), whenDeployed->{
      System.out.println("Deployed " + VerticleAB.class.getName());
      // Do not undeploy
    });
    startPromise.complete();
  }
}
