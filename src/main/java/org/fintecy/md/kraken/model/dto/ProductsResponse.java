package org.fintecy.md.kraken.model.dto;

import org.fintecy.md.kraken.model.MicroType;
import org.fintecy.md.kraken.model.Product;

import java.util.List;

import static java.lang.String.join;

public class ProductsResponse extends MicroType<List<Product>> {
    private final List<String> errors;

    public ProductsResponse(List<Product> value, List<String> errors) {
        super(value);
        this.errors = errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    public List<Product> getProductsOrThrow() {
        if (hasErrors())
            throw new IllegalStateException(join(",", errors));
        else
            return getValue();
    }
}
