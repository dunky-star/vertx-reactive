package com.dunky.vertex.vertex_starter.json;

public class Person {
  private Integer id;
  private String name;
  private boolean lovesVertex;

  // Required by Jackson XML
  public Person() {

  }

  public Person(Integer id, String name, boolean lovesVertex) {
    this.id = id;
    this.name = name;
    this.lovesVertex = lovesVertex;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public boolean isLovesVertex() {
    return lovesVertex;
  }
}
