package org.fintecy.md.kraken.serialization.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.Asset;

import java.io.IOException;
import java.util.Set;

public class AssetDeserializer extends StdDeserializer<Asset> {
    public final static AssetDeserializer INSTANCE = new AssetDeserializer();
    public static final Set<String> REQUIRED_FIELDS = Set.of("aclass", "altname", "decimals", "display_decimals");

    public AssetDeserializer() {
        super(Asset.class);
    }

    public static Asset parse(JsonParser jp, String id, JsonNode node) {
        for (String field : REQUIRED_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }

        return new Asset(
                id,
                node.get("altname").asText(),
                node.get("aclass").asText(),
                node.get("decimals").asInt(),
                node.get("display_decimals").asInt()
        );
    }

    @Override
    public Asset deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, "unknown", node);
    }
}
