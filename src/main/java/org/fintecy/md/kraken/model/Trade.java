package org.fintecy.md.kraken.model;

import java.time.Instant;
import java.util.Objects;

public class Trade {
    private final ProductCode pair;
    private final Instant timestamp;
    private final PriceLevel price;
    private final OrderType type;
    private final Side side;

    public Trade(ProductCode pair, Instant timestamp, PriceLevel price, OrderType type, Side side) {
        this.pair = pair;
        this.timestamp = timestamp;
        this.price = price;
        this.type = type;
        this.side = side;
    }

    public ProductCode getPair() {
        return pair;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public PriceLevel getPrice() {
        return price;
    }

    public OrderType getType() {
        return type;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Objects.equals(pair, trade.pair)
                && Objects.equals(timestamp, trade.timestamp)
                && Objects.equals(price, trade.price)
                && type == trade.type
                && side == trade.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pair, timestamp, price, type, side);
    }

    @Override
    public String toString() {
        return "Trade{" +
                "pair=" + pair +
                ", timestamp=" + timestamp +
                ", price=" + price +
                ", type=" + type +
                ", side=" + side +
                '}';
    }
}
