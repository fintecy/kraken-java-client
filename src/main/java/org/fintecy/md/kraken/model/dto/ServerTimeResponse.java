package org.fintecy.md.kraken.model.dto;

import java.time.Instant;
import java.util.List;

public class ServerTimeResponse extends DataResponse<Instant> {
    public ServerTimeResponse(Instant value, List<String> error) {
        super(value, error);
    }
}
