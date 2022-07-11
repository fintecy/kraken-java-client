package org.fintecy.md.kraken.model.dto;

import org.fintecy.md.kraken.model.Candle;

import java.util.List;

public class OHLCResponse extends DataResponse<List<Candle>> {
    public OHLCResponse(List<Candle> value, List<String> errors) {
        super(value, errors);
    }
}
