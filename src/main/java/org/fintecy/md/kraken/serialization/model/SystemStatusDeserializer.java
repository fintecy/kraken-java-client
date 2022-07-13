package org.fintecy.md.kraken.serialization.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.kraken.model.SystemStatus;

import java.io.IOException;
import java.time.Instant;
import java.util.Set;

public class SystemStatusDeserializer extends StdDeserializer<SystemStatus> {
    public final static SystemStatusDeserializer INSTANCE = new SystemStatusDeserializer();
    public static final Set<String> REQUIRED_FIELDS = Set.of("result");

    public SystemStatusDeserializer() {
        super(SystemStatus.class);
    }

    public static SystemStatus parse(JsonParser jp, JsonNode node) {
        for (String field : REQUIRED_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }
        JsonNode result = node.get("result");
        return new SystemStatus(
                result.get("status").asText(),
                Instant.parse(result.get("timestamp").asText()));
    }

    @Override
    public SystemStatus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, node);
    }
}
