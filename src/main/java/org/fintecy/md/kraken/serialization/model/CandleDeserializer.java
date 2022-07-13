package org.fintecy.md.kraken.serialization.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.Candle;
import org.fintecy.md.kraken.model.ProductCode;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import static org.fintecy.md.kraken.model.ProductCode.product;

public class CandleDeserializer extends StdDeserializer<Candle> {
    public final static CandleDeserializer INSTANCE = new CandleDeserializer();
    public static final Set<String> REQUIRED_FIELDS = Set.of();

    public CandleDeserializer() {
        super(Candle.class);
    }

    public static Candle parse(JsonParser jp, ProductCode pair, JsonNode node) {
        for (String field : REQUIRED_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }

        var time = Instant.ofEpochSecond(node.get(0).asLong());
        var open = new BigDecimal(node.get(1).asText());
        var high = new BigDecimal(node.get(2).asText());
        var low = new BigDecimal(node.get(3).asText());
        var closed = new BigDecimal(node.get(4).asText());
        var vwap = new BigDecimal(node.get(5).asText());
        var volume = new BigDecimal(node.get(6).asText());
        var tradeCount = node.get(7).asInt();
        return new Candle(pair, time, open, high, low, closed, vwap, volume, tradeCount);
    }

    @Override
    public Candle deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, product("unknown"), node);
    }
}
