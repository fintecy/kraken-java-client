package org.fintecy.md.kraken.model.dto;

import org.fintecy.md.kraken.model.ExchangeRate;
import org.fintecy.md.kraken.model.MicroType;

import java.util.List;

import static java.lang.String.join;

public class TickerResponse extends MicroType<List<ExchangeRate>> {
    private final List<String> errors;

    public TickerResponse(List<ExchangeRate> value, List<String> errors) {
        super(value);
        this.errors = errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    public List<ExchangeRate> getRateOrThrow() {
        if (hasErrors())
            throw new IllegalStateException(join(",", errors));
        else
            return getValue();
    }
}
