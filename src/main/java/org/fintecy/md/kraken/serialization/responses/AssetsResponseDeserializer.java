package org.fintecy.md.kraken.serialization.responses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.fintecy.md.kraken.model.Asset;
import org.fintecy.md.kraken.model.dto.AssetsResponse;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.fintecy.md.kraken.serialization.model.AssetDeserializer.parse;

public class AssetsResponseDeserializer extends DefaultResponseDeserializer<AssetsResponse> {
    public final static AssetsResponseDeserializer INSTANCE = new AssetsResponseDeserializer();

    public AssetsResponseDeserializer() {
        super(AssetsResponse.class);
    }

    @Override
    protected AssetsResponse errorResponse(List<String> errors) {
        return new AssetsResponse(emptyList(), errors);
    }

    @Override
    protected AssetsResponse dataResponse(JsonParser jp, JsonNode resultNode) {
        List<Asset> assets = new ArrayList<>();
        resultNode.fieldNames().forEachRemaining(s -> {
            JsonNode itemNode = resultNode.get(s);
            assets.add(parse(jp, s, itemNode));
        });
        return new AssetsResponse(assets, emptyList());
    }
}
