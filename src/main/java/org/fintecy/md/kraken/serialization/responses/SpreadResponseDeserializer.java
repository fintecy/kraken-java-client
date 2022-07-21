package org.fintecy.md.kraken.serialization.responses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.fintecy.md.kraken.model.Spread;
import org.fintecy.md.kraken.model.dto.SpreadResponse;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.fintecy.md.kraken.model.ProductCode.product;
import static org.fintecy.md.kraken.serialization.model.SpreadDeserializer.parse;

public class SpreadResponseDeserializer extends DefaultResponseDeserializer<SpreadResponse> {
    public final static SpreadResponseDeserializer INSTANCE = new SpreadResponseDeserializer();

    public SpreadResponseDeserializer() {
        super(SpreadResponse.class);
    }

    @Override
    protected SpreadResponse errorResponse(List<String> errors) {
        return new SpreadResponse(emptyList(), errors);
    }

    @Override
    protected SpreadResponse dataResponse(JsonParser jp, JsonNode resultNode) {
        List<Spread> result = new ArrayList<>();
        resultNode.fieldNames().forEachRemaining(s -> {
            if (!s.equals("last")) {
                resultNode.get(s).forEach(jsonNode ->
                        result.add(parse(jp, product(s), jsonNode)));
            }
        });
        return new SpreadResponse(result, emptyList());
    }
}
