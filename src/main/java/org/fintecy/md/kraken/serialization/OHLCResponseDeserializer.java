package org.fintecy.md.kraken.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.Candle;
import org.fintecy.md.kraken.model.ProductCode;
import org.fintecy.md.kraken.model.dto.OHLCResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.fintecy.md.kraken.serialization.CandleDeserializer.parse;

public class OHLCResponseDeserializer extends StdDeserializer<OHLCResponse> {
    public final static OHLCResponseDeserializer INSTANCE = new OHLCResponseDeserializer();

    public OHLCResponseDeserializer() {
        super(OHLCResponse.class);
    }

    @Override
    public OHLCResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);

        JsonNode resultNode = node.get("result");
        if (node.has("error") && !node.get("error").isNull() && node.get("error").size() != 0) {
            List<String> errors = new ArrayList<>();
            node.get("error").forEach(jsonNode -> errors.add(jsonNode.asText()));
            return new OHLCResponse(null, errors);
        } else {
            List<Candle> rates = new ArrayList<>();
            resultNode.fieldNames().forEachRemaining(s -> {
                resultNode.get(s).forEach(candleNode ->
                        rates.add(parse(jp, ProductCode.product(s), candleNode)));
            });
            return new OHLCResponse(rates, null);
        }
    }
}
