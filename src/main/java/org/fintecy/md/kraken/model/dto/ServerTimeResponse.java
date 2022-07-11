package org.fintecy.md.kraken.model.dto;

import org.fintecy.md.kraken.model.MicroType;

import java.time.Instant;
import java.util.List;

public class ServerTimeResponse extends MicroType<Instant> {
    private final List<String> error;

    public ServerTimeResponse(Instant value, List<String> error) {
        super(value);
        this.error = error;
    }

    public static ServerTimeResponse errorResponse(List<String> error) {
        return new ServerTimeResponse(Instant.EPOCH, error);
    }

    public static ServerTimeResponse dataResponse(Instant serverTime) {
        return new ServerTimeResponse(serverTime, null);
    }

    public Instant serverTime() {
        return getValue();
    }

    public List<String> errors() {
        return error == null ? List.of() : error;
    }
}
