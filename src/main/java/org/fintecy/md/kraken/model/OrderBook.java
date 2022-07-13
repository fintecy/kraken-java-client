package org.fintecy.md.kraken.model;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class OrderBook {
    private final ProductCode pair;
    private final Instant timestamp;
    private final List<PriceLevel> bids;
    private final List<PriceLevel> asks;

    public OrderBook(ProductCode pair, Instant timestamp, List<PriceLevel> bids, List<PriceLevel> asks) {
        this.pair = pair;
        this.timestamp = timestamp;
        this.bids = bids;
        this.asks = asks;
    }

    public ProductCode getPair() {
        return pair;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public List<PriceLevel> getBids() {
        return bids;
    }

    public List<PriceLevel> getAsks() {
        return asks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBook orderBook = (OrderBook) o;
        return Objects.equals(pair, orderBook.pair)
                && Objects.equals(timestamp, orderBook.timestamp)
                && Objects.equals(bids, orderBook.bids)
                && Objects.equals(asks, orderBook.asks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pair, timestamp, bids, asks);
    }

    @Override
    public String toString() {
        return "OrderBook{" +
                "pair=" + pair +
                "timestamp=" + timestamp +
                ", bids=" + bids +
                ", asks=" + asks +
                '}';
    }
}
