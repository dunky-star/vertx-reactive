package com.dunky.vertex.vertex_starter.eventbus.customcodec;

public class Pong {

  private Integer id;

  public Pong() {
    // Default Constructor
  }

  public Pong(final Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Pong{" +
      "id=" + id +
      '}';
  }
}
