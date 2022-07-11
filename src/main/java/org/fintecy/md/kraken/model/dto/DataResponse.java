package org.fintecy.md.kraken.model.dto;

import org.fintecy.md.kraken.model.MicroType;

import java.util.List;

import static java.lang.String.join;

public class DataResponse<T> extends MicroType<T> {
    private final List<String> errors;

    public DataResponse(T value, List<String> errors) {
        super(value);
        this.errors = errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    public T getDataOrThrow() {
        if (hasErrors())
            throw new IllegalStateException(join(",", errors));
        else
            return getValue();
    }
}
