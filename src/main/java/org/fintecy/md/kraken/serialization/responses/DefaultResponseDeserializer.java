package org.fintecy.md.kraken.serialization.responses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.dto.DataResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class DefaultResponseDeserializer<T extends DataResponse<?>> extends StdDeserializer<T> {
    public DefaultResponseDeserializer(Class<T> tClass) {
        super(tClass);
    }

    protected abstract T errorResponse(List<String> errors);

    protected abstract T dataResponse(JsonParser jp, JsonNode resultNode);

    @Override
    public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);

        JsonNode resultNode = node.get("result");
        if (node.has("error") && !node.get("error").isNull() && node.get("error").size() != 0) {
            List<String> errors = new ArrayList<>();
            node.get("error").forEach(jsonNode -> errors.add(jsonNode.asText()));
            return errorResponse(errors);
        } else {
            return dataResponse(jp, resultNode);
        }
    }
}
