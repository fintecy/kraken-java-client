package org.fintecy.md.kraken.serialization.responses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.fintecy.md.kraken.model.Candle;
import org.fintecy.md.kraken.model.ProductCode;
import org.fintecy.md.kraken.model.dto.OHLCResponse;

import java.util.ArrayList;
import java.util.List;

import static org.fintecy.md.kraken.serialization.model.CandleDeserializer.parse;

public class OHLCResponseDeserializer extends DefaultResponseDeserializer<OHLCResponse> {
    public final static OHLCResponseDeserializer INSTANCE = new OHLCResponseDeserializer();

    public OHLCResponseDeserializer() {
        super(OHLCResponse.class);
    }

    @Override
    protected OHLCResponse errorResponse(List<String> errors) {
        return new OHLCResponse(null, errors);
    }

    @Override
    protected OHLCResponse dataResponse(JsonParser jp, JsonNode resultNode) {
        List<Candle> rates = new ArrayList<>();
        resultNode.fieldNames().forEachRemaining(s -> {
            resultNode.get(s).forEach(candleNode ->
                    rates.add(parse(jp, ProductCode.product(s), candleNode)));
        });
        return new OHLCResponse(rates, null);
    }
}
