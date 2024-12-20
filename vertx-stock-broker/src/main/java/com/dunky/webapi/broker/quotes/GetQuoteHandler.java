package com.dunky.webapi.broker.quotes;
import java.util.Map;
import java.util.Optional;

import com.dunky.webapi.broker.db.migration.DbResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import io.vertx.core.json.JsonObject;

public class GetQuoteHandler implements Handler<RoutingContext> {

  private static final Logger LOG = LoggerFactory.getLogger(GetQuoteHandler.class);
  private final Map<String, Quote> cachedQuotes;

  public GetQuoteHandler(final Map<String, Quote> cachedQuotes) {
    this.cachedQuotes = cachedQuotes;
  }

  @Override
  public void handle(final RoutingContext context) {
    final String assetParam = context.pathParam("asset");
    LOG.debug("Asset parameter: {}", assetParam);

    var maybeQuote = Optional.ofNullable(cachedQuotes.get(assetParam));
    if (maybeQuote.isEmpty()) {
      DbResponse.notFound(context, "quote for asset " + assetParam + " not available!");
      return;
    }

    final JsonObject response = maybeQuote.get().toJsonObject();
    LOG.info("Path {} responds with {}", context.normalizedPath(), response.encode());
    context.response().end(response.toBuffer());
  }
}
