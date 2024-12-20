package com.dunky.vertex.vertex_starter.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class JsonObjectExampleTest {

  @Test
  void jsonObjectCanBeMapped() {
    final JsonObject myJsonObject = new JsonObject();
    myJsonObject.put("id", 1);
    myJsonObject.put("name", "Dunky");
    myJsonObject.put("loves_vertx", true);

    final String encoded = myJsonObject.encode();

    assertEquals("{\"id\":1,\"name\":\"Dunky\",\"loves_vertx\":true}", encoded);

    final JsonObject decodedJsonObject = new JsonObject(encoded);
    assertEquals(myJsonObject, decodedJsonObject);
  }

  @Test
  void jsonObjectCanBeCreatedFromMap() {
    final Map<String, Object> myMap = new HashMap<>();
    myMap.put("id", 1);
    myMap.put("name", "Alice");
    myMap.put("loves_vertx", true);
    final JsonObject asJsonObject = new JsonObject(myMap);
    assertEquals(myMap, asJsonObject.getMap());
    assertEquals(1, asJsonObject.getInteger("id"));
    assertEquals("Alice", asJsonObject.getString("name"));
    assertEquals(true, asJsonObject.getBoolean("loves_vertx"));
  }

  @Test
  void jsonArrayCanBeMapped() {
    final JsonArray myJsonArray = new JsonArray();
    myJsonArray
      .add(new JsonObject().put("id", 1))
      .add(new JsonObject().put("id", 2))
      .add(new JsonObject().put("id", 3))
      .add("randomValue")
    ;
    assertEquals("[{\"id\":1},{\"id\":2},{\"id\":3},\"randomValue\"]", myJsonArray.encode());
  }

  @Test
  void canMapJavaObjects() {
    final Person person = new Person(1, "Duncan", true);
    final JsonObject dunky = JsonObject.mapFrom(person);
    assertEquals(person.getId(), dunky.getInteger("id"));
    assertEquals(person.getName(), dunky.getString("name"));
    assertEquals(person.isLovesVertex(), dunky.getBoolean("lovesVertex"));

    final Person person2 = dunky.mapTo(Person.class);
    assertEquals(person.getId(), person2.getId());
    assertEquals(person.getName(), person2.getName());
    assertEquals(person.isLovesVertex(), person2.isLovesVertex());
  }

}
