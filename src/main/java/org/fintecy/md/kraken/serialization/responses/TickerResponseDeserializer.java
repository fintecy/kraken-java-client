package org.fintecy.md.kraken.serialization.responses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.fintecy.md.kraken.model.ExchangeRate;
import org.fintecy.md.kraken.model.ProductCode;
import org.fintecy.md.kraken.model.dto.TickerResponse;

import java.util.ArrayList;
import java.util.List;

import static org.fintecy.md.kraken.serialization.model.ExchangeRateDeserializer.parse;

public class TickerResponseDeserializer extends DefaultResponseDeserializer<TickerResponse> {
    public final static TickerResponseDeserializer INSTANCE = new TickerResponseDeserializer();

    public TickerResponseDeserializer() {
        super(TickerResponse.class);
    }

    @Override
    protected TickerResponse errorResponse(List<String> errors) {
        return new TickerResponse(null, errors);
    }

    @Override
    protected TickerResponse dataResponse(JsonParser jp, JsonNode resultNode) {
        List<ExchangeRate> rates = new ArrayList<>();
        resultNode.fieldNames().forEachRemaining(s -> {
            JsonNode itemNode = resultNode.get(s);
            rates.add(parse(jp, ProductCode.product(s), itemNode));
        });
        return new TickerResponse(rates, null);
    }
}
