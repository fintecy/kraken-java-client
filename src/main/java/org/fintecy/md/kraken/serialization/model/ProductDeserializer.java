package org.fintecy.md.kraken.serialization.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProductDeserializer extends StdDeserializer<Product> {
    public final static ProductDeserializer INSTANCE = new ProductDeserializer();
    public static final Set<String> REQUIRED_FIELDS = Set.of("altname", "wsname", "aclass_base", "base", "aclass_quote",
            "quote", "lot", "pair_decimals", "lot_decimals", "lot_multiplier");

    public ProductDeserializer() {
        super(Product.class);
    }

    public static Product parse(JsonParser jp, String id, JsonNode node) {
        for (String field : REQUIRED_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field '" + field + "' is missing");
        }

        return new Product(id,
                node.get("altname").asText(),
                node.get("wsname").asText(),
                node.get("aclass_base").asText(),
                node.get("base").asText(),
                node.get("aclass_quote").asText(),
                node.get("quote").asText(),
                node.get("lot").asText(),
                node.get("pair_decimals").asInt(),
                node.get("lot_decimals").asInt(),
                node.get("lot_multiplier").asInt(),
                intArray(node.get("leverage_buy")),
                intArray(node.get("leverage_sell")),
                longDoubleMap(node.get("fees")),
                longDoubleMap(node.get("fees_maker")),
                node.get("fee_volume_currency").asText(),
                node.get("margin_call").asInt(),
                node.get("margin_stop").asInt(),
                new BigDecimal(node.get("ordermin").asText())
        );
    }

    private static Map<Long, Double> longDoubleMap(JsonNode fees) {
        Map<Long, Double> result = new HashMap<>();
        for (JsonNode jsonNode : fees) {
            if (jsonNode.size() == 2) {
                result.put(jsonNode.get(0).asLong(), jsonNode.get(1).asDouble());
            }
        }
        return result;
    }

    private static int[] intArray(JsonNode node) {
        int[] result = new int[node.size()];
        for (int i = 0; i < node.size(); i++) {
            result[i] = node.get(i).asInt();
        }
        return result;
    }

    @Override
    public Product deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, "unknown", node);
    }
}
