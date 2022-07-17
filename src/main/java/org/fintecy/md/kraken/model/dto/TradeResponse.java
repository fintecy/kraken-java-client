package org.fintecy.md.kraken.model.dto;

import org.fintecy.md.kraken.model.Trade;

import java.util.List;

public class TradeResponse extends DataResponse<List<Trade>> {
    public TradeResponse(List<Trade> value, List<String> errors) {
        super(value, errors);
    }
}
