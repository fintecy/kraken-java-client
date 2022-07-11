package org.fintecy.md.kraken.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.ExchangeRate;
import org.fintecy.md.kraken.model.PriceLevel;
import org.fintecy.md.kraken.model.ProductCode;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import static java.time.temporal.ChronoUnit.SECONDS;

public class ExchangeRateDeserializer extends StdDeserializer<ExchangeRate> {
    public final static ExchangeRateDeserializer INSTANCE = new ExchangeRateDeserializer();
    public static final Set<String> REQUIRED_FIELDS = Set.of("a", "b", "c", "v", "p", "t", "l", "h", "o");

    public ExchangeRateDeserializer() {
        super(ExchangeRate.class);
    }

    public static ExchangeRate parse(JsonParser jp, ProductCode pair, JsonNode node) {
        for (String field : REQUIRED_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }

        var ask = new PriceLevel(
                new BigDecimal(node.get("a").get(0).asText()),
                new BigDecimal(node.get("a").get(2).asText()),
                1
        );
        var bid = new PriceLevel(
                new BigDecimal(node.get("b").get(0).asText()),
                new BigDecimal(node.get("b").get(2).asText()),
                1
        );
        var closed = new BigDecimal(node.get("c").get(0).asText());
        var volume = new BigDecimal(node.get("v").get(0).asText());
        var vwap = new BigDecimal(node.get("p").get(0).asText());
        var tradeCount = node.get("t").get(0).asLong();
        var low = new BigDecimal(node.get("l").get(0).asText());
        var high = new BigDecimal(node.get("h").get(0).asText());
        var open = new BigDecimal(node.get("o").asText());
        return new ExchangeRate(pair, Instant.now().truncatedTo(SECONDS), ask, bid, closed, volume, vwap, tradeCount, low, high, open);
    }

    @Override
    public ExchangeRate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, ProductCode.product("unknown"), node);
    }
}
