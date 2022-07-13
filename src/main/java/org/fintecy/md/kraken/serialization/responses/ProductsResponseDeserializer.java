package org.fintecy.md.kraken.serialization.responses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.fintecy.md.kraken.model.Product;
import org.fintecy.md.kraken.model.dto.ProductsResponse;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.fintecy.md.kraken.serialization.model.ProductDeserializer.parse;

public class ProductsResponseDeserializer extends DefaultResponseDeserializer<ProductsResponse> {
    public final static ProductsResponseDeserializer INSTANCE = new ProductsResponseDeserializer();

    public ProductsResponseDeserializer() {
        super(ProductsResponse.class);
    }

    @Override
    protected ProductsResponse errorResponse(List<String> errors) {
        return new ProductsResponse(emptyList(), errors);
    }

    @Override
    protected ProductsResponse dataResponse(JsonParser jp, JsonNode resultNode) {
        List<Product> assets = new ArrayList<>();
        resultNode.fieldNames().forEachRemaining(s -> {
            JsonNode itemNode = resultNode.get(s);
            assets.add(parse(jp, s, itemNode));
        });
        return new ProductsResponse(assets, emptyList());
    }
}
