package org.fintecy.md.kraken.model.dto;

import org.fintecy.md.kraken.model.ExchangeRate;

import java.util.List;

public class TickerResponse extends DataResponse<List<ExchangeRate>> {
    public TickerResponse(List<ExchangeRate> value, List<String> errors) {
        super(value, errors);
    }
}
