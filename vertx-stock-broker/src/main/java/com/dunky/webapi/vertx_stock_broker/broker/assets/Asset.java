package com.dunky.webapi.vertx_stock_broker.broker.assets;

public class Asset {
  private final String name;

  public Asset(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}