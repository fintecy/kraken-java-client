package org.fintecy.md.kraken.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.Product;
import org.fintecy.md.kraken.model.dto.ProductsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.fintecy.md.kraken.serialization.ProductDeserializer.parse;

public class ProductsResponseDeserializer extends StdDeserializer<ProductsResponse> {
    public final static ProductsResponseDeserializer INSTANCE = new ProductsResponseDeserializer();

    public ProductsResponseDeserializer() {
        super(ProductsResponse.class);
    }

    @Override
    public ProductsResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);

        JsonNode resultNode = node.get("result");
        if (node.has("error") && !node.get("error").isNull() && node.get("error").size() != 0) {
            List<String> errors = new ArrayList<>();
            node.get("error").forEach(jsonNode -> errors.add(jsonNode.asText()));
            return new ProductsResponse(emptyList(), errors);
        } else {
            List<Product> assets = new ArrayList<>();
            resultNode.fieldNames().forEachRemaining(s -> {
                JsonNode itemNode = resultNode.get(s);
                assets.add(parse(jp, s, itemNode));
            });
            return new ProductsResponse(assets, emptyList());
        }
    }
}
