package org.fintecy.md.kraken.model.dto;

import org.fintecy.md.kraken.model.Spread;

import java.util.List;

public class SpreadResponse extends DataResponse<List<Spread>> {
    public SpreadResponse(List<Spread> value, List<String> errors) {
        super(value, errors);
    }
}
