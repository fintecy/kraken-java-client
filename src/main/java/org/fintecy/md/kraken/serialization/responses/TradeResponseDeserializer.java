package org.fintecy.md.kraken.serialization.responses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.fintecy.md.kraken.model.Trade;
import org.fintecy.md.kraken.model.dto.TradeResponse;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.fintecy.md.kraken.model.ProductCode.product;
import static org.fintecy.md.kraken.serialization.model.TradeDeserializer.parse;

public class TradeResponseDeserializer extends DefaultResponseDeserializer<TradeResponse> {
    public final static TradeResponseDeserializer INSTANCE = new TradeResponseDeserializer();

    public TradeResponseDeserializer() {
        super(TradeResponse.class);
    }

    @Override
    protected TradeResponse errorResponse(List<String> errors) {
        return new TradeResponse(emptyList(), errors);
    }

    @Override
    protected TradeResponse dataResponse(JsonParser jp, JsonNode resultNode) {
        List<Trade> result = new ArrayList<>();
        resultNode.fieldNames().forEachRemaining(s -> {
            if (!s.equals("last")) {
                resultNode.get(s).forEach(jsonNode ->
                        result.add(parse(jp, product(s), jsonNode)));
            }
        });
        return new TradeResponse(result, emptyList());
    }
}
