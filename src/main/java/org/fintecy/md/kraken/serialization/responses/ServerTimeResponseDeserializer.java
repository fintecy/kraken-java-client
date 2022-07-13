package org.fintecy.md.kraken.serialization.responses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.fintecy.md.kraken.model.dto.ServerTimeResponse;

import java.util.List;

import static java.time.Instant.EPOCH;
import static java.time.Instant.ofEpochSecond;
import static java.util.Collections.emptyList;

public class ServerTimeResponseDeserializer extends DefaultResponseDeserializer<ServerTimeResponse> {
    public final static ServerTimeResponseDeserializer INSTANCE = new ServerTimeResponseDeserializer();

    public ServerTimeResponseDeserializer() {
        super(ServerTimeResponse.class);
    }

    @Override
    protected ServerTimeResponse errorResponse(List<String> errors) {
        return new ServerTimeResponse(EPOCH, errors);
    }

    @Override
    protected ServerTimeResponse dataResponse(JsonParser jp, JsonNode resultNode) {
        var serverTime = ofEpochSecond(resultNode.get("unixtime").asLong());
        return new ServerTimeResponse(serverTime, emptyList());
    }
}
