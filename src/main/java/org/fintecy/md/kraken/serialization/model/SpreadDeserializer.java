package org.fintecy.md.kraken.serialization.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.ProductCode;
import org.fintecy.md.kraken.model.Spread;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import static org.fintecy.md.kraken.model.ProductCode.product;

public class SpreadDeserializer extends StdDeserializer<Spread> {
    public final static SpreadDeserializer INSTANCE = new SpreadDeserializer();
    public static final Set<String> REQUIRED_FIELDS = Set.of();

    public SpreadDeserializer() {
        super(Spread.class);
    }

    public static Spread parse(JsonParser jp, ProductCode pair, JsonNode node) {
        for (String field : REQUIRED_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }

        var ts = Instant.ofEpochSecond(node.get(0).asLong());
        var bid = new BigDecimal(node.get(1).asText());
        var ask = new BigDecimal(node.get(2).asText());
        return new Spread(pair, ts, bid, ask);
    }

    @Override
    public Spread deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, product("unknown"), node);
    }
}
