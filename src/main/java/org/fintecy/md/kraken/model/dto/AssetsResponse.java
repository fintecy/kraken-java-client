package org.fintecy.md.kraken.model.dto;

import org.fintecy.md.kraken.model.Asset;
import org.fintecy.md.kraken.model.MicroType;

import java.util.List;

import static java.lang.String.join;

public class AssetsResponse extends MicroType<List<Asset>> {
    private final List<String> errors;

    public AssetsResponse(List<Asset> value, List<String> errors) {
        super(value);
        this.errors = errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    public List<Asset> getAssetsOrThrow() {
        if (hasErrors())
            throw new IllegalStateException(join(",", errors));
        else
            return getValue();
    }
}
