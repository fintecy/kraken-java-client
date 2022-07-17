package org.fintecy.md.kraken.serialization.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.PriceLevel;
import org.fintecy.md.kraken.model.ProductCode;
import org.fintecy.md.kraken.model.Trade;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

import static java.time.Instant.ofEpochMilli;
import static org.fintecy.md.kraken.model.OrderType.orderType;
import static org.fintecy.md.kraken.model.ProductCode.product;
import static org.fintecy.md.kraken.model.Side.side;

public class TradeDeserializer extends StdDeserializer<Trade> {
    public final static TradeDeserializer INSTANCE = new TradeDeserializer();
    public static final Set<String> REQUIRED_FIELDS = Set.of();

    public TradeDeserializer() {
        super(Trade.class);
    }

    public static Trade parse(JsonParser jp, ProductCode pair, JsonNode node) {
        for (String field : REQUIRED_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }

        var price = new BigDecimal(node.get(0).asText());
        var volume = new BigDecimal(node.get(1).asText());
        var ts = ofEpochMilli((long) (node.get(2).asDouble() * 1000));
        var type = orderType(node.get(4).asText());
        var side = side(node.get(3).asText());
        return new Trade(pair, ts, new PriceLevel(price, volume, 0L), type, side);
    }

    @Override
    public Trade deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, product("unknown"), node);
    }
}
