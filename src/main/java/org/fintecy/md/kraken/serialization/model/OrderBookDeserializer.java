package org.fintecy.md.kraken.serialization.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.OrderBook;
import org.fintecy.md.kraken.model.PriceLevel;
import org.fintecy.md.kraken.model.ProductCode;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.fintecy.md.kraken.model.ProductCode.product;

public class OrderBookDeserializer extends StdDeserializer<OrderBook> {
    public final static OrderBookDeserializer INSTANCE = new OrderBookDeserializer();
    public static final Set<String> REQUIRED_FIELDS = Set.of("asks", "bids");

    public OrderBookDeserializer() {
        super(OrderBook.class);
    }

    public static OrderBook parse(JsonParser jp, ProductCode pair, JsonNode node) {
        for (String field : REQUIRED_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }

        var asks = getPriceLevels(node.get("asks"));
        var bids = getPriceLevels(node.get("bids"));
        var aTs = asks.stream().map(PriceLevel::getLevel).max(Long::compareTo).map(Instant::ofEpochSecond).orElse(Instant.MIN);
        var bTs = bids.stream().map(PriceLevel::getLevel).max(Long::compareTo).map(Instant::ofEpochSecond).orElse(Instant.MIN);
        var ts = aTs.compareTo(bTs) > 0 ? aTs : bTs;
        return new OrderBook(pair, ts, asks, bids);
    }

    private static List<PriceLevel> getPriceLevels(JsonNode resultNode) {
        List<PriceLevel> res = new ArrayList<>();
        resultNode.forEach((askNode -> {
            var price = new BigDecimal(askNode.get(0).asText());
            var volume = new BigDecimal(askNode.get(1).asText());
            var levelTs = askNode.get(2).asLong();
            res.add(new PriceLevel(price, volume, levelTs));
        }));
        return res;
    }

    @Override
    public OrderBook deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, product("unknown"), node);
    }
}
