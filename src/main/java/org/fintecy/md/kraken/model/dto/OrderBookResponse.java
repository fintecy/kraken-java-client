package org.fintecy.md.kraken.model.dto;

import org.fintecy.md.kraken.model.OrderBook;
import org.fintecy.md.kraken.model.Product;

import java.util.List;

public class OrderBookResponse extends DataResponse<List<OrderBook>> {
    public OrderBookResponse(List<OrderBook> value, List<String> errors) {
        super(value, errors);
    }
}
