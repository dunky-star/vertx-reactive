package com.dunky.webapi.broker.assets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.dunky.webapi.vertx_stock_broker.AbstractRestApiTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.junit5.VertxExtension;

@ExtendWith(VertxExtension.class)
public class TestAssetsRestApi extends AbstractRestApiTest {

  private static final Logger LOG = LoggerFactory.getLogger(TestAssetsRestApi.class);

//  @Test
//  void returns_all_assets(Vertx vertx, VertxTestContext context) throws Throwable {
//    var client = WebClient.create(vertx, new WebClientOptions()
//      .setDefaultPort(TEST_SERVER_PORT));
//    client.get("/assets")
//      .send()
//      .onComplete(context.succeeding(response -> {
//        var json = response.bodyAsJsonArray();
//        LOG.info("Response: {}", json);
//        assertEquals("[{\"name\":\"AAPL\"},{\"name\":\"AMZN\"},{\"name\":\"FB\"},{\"name\":\"GOOG\"},{\"name\":\"MSFT\"},{\"name\":\"NFLX\"},{\"name\":\"TSLA\"}]", json.encode());
//        assertEquals(200, response.statusCode());
//        assertEquals(HttpHeaderValues.APPLICATION_JSON.toString(),
//          response.getHeader(HttpHeaders.CONTENT_TYPE.toString()));
//        assertEquals("my-value", response.getHeader("my-header"));
//        context.completeNow();
//      }));
//  }
}

