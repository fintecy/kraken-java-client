package org.fintecy.md.kraken.serialization.responses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.fintecy.md.kraken.model.OrderBook;
import org.fintecy.md.kraken.model.dto.OrderBookResponse;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.fintecy.md.kraken.model.ProductCode.product;
import static org.fintecy.md.kraken.serialization.model.OrderBookDeserializer.parse;

public class OrderBookResponseDeserializer extends DefaultResponseDeserializer<OrderBookResponse> {
    public final static OrderBookResponseDeserializer INSTANCE = new OrderBookResponseDeserializer();

    public OrderBookResponseDeserializer() {
        super(OrderBookResponse.class);
    }

    @Override
    protected OrderBookResponse errorResponse(List<String> errors) {
        return new OrderBookResponse(null, errors);
    }

    @Override
    protected OrderBookResponse dataResponse(JsonParser jp, JsonNode resultNode) {
        List<OrderBook> result = new ArrayList<>();
        resultNode.fieldNames().forEachRemaining(s ->
                result.add(parse(jp, product(s), resultNode.get(s))));
        return new OrderBookResponse(result, emptyList());
    }
}
