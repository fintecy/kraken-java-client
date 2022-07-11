package org.fintecy.md.kraken.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.ExchangeRate;
import org.fintecy.md.kraken.model.ProductCode;
import org.fintecy.md.kraken.model.dto.TickerResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.fintecy.md.kraken.serialization.ExchangeRateDeserializer.parse;

public class TickerResponseDeserializer extends StdDeserializer<TickerResponse> {
    public final static TickerResponseDeserializer INSTANCE = new TickerResponseDeserializer();

    public TickerResponseDeserializer() {
        super(ExchangeRate.class);
    }

    @Override
    public TickerResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);

        JsonNode resultNode = node.get("result");
        if (node.has("error") && !node.get("error").isNull() && node.get("error").size() != 0) {
            List<String> errors = new ArrayList<>();
            node.get("error").forEach(jsonNode -> errors.add(jsonNode.asText()));
            return new TickerResponse(null, errors);
        } else {
            List<ExchangeRate> rates = new ArrayList<>();
            resultNode.fieldNames().forEachRemaining(s -> {
                JsonNode itemNode = resultNode.get(s);
                rates.add(parse(jp, ProductCode.product(s), itemNode));
            });
            return new TickerResponse(rates, null);
        }
    }
}
