package org.fintecy.md.kraken.model.dto;

import org.fintecy.md.kraken.model.Product;

import java.util.List;

public class ProductsResponse extends DataResponse<List<Product>> {
    public ProductsResponse(List<Product> value, List<String> errors) {
        super(value, errors);
    }
}
