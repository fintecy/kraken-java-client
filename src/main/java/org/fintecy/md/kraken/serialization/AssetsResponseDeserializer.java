package org.fintecy.md.kraken.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.Asset;
import org.fintecy.md.kraken.model.dto.AssetsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.fintecy.md.kraken.serialization.AssetDeserializer.parse;

public class AssetsResponseDeserializer extends StdDeserializer<AssetsResponse> {
    public final static AssetsResponseDeserializer INSTANCE = new AssetsResponseDeserializer();

    public AssetsResponseDeserializer() {
        super(AssetsResponse.class);
    }

    @Override
    public AssetsResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);

        JsonNode resultNode = node.get("result");
        if (node.has("error") && !node.get("error").isNull() && node.get("error").size() != 0) {
            List<String> errors = new ArrayList<>();
            node.get("error").forEach(jsonNode -> errors.add(jsonNode.asText()));
            return new AssetsResponse(emptyList(), errors);
        } else {
            List<Asset> assets = new ArrayList<>();
            resultNode.fieldNames().forEachRemaining(s -> {
                JsonNode itemNode = resultNode.get(s);
                assets.add(parse(jp, s, itemNode));
            });
            return new AssetsResponse(assets, emptyList());
        }
    }
}
