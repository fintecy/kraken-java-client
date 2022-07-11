package org.fintecy.md.kraken.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.dto.ServerTimeResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.time.Instant.ofEpochSecond;

public class ServerTimeResponseDeserializer extends StdDeserializer<ServerTimeResponse> {
    public final static ServerTimeResponseDeserializer INSTANCE = new ServerTimeResponseDeserializer();

    public ServerTimeResponseDeserializer() {
        super(ServerTimeResponse.class);
    }

    @Override
    public ServerTimeResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);

        if (node.has("error") && node.get("error").isNull() && node.get("error").size() != 0) {
            List<String> errors = new ArrayList<>();
            node.get("error").forEach(jsonNode ->
                    errors.add(jsonNode.asText()));
            return ServerTimeResponse.errorResponse(errors);
        }
        var serverTime = ofEpochSecond(node.get("result").get("unixtime").asLong());
        return ServerTimeResponse.dataResponse(serverTime);
    }
}
