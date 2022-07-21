package org.fintecy.md.kraken.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Spread {
    private final ProductCode pair;
    private final Instant timestamp;
    private final BigDecimal ask;
    private final BigDecimal bid;

    public Spread(ProductCode pair, Instant timestamp, BigDecimal ask, BigDecimal bid) {
        this.pair = pair;
        this.timestamp = timestamp;
        this.ask = ask;
        this.bid = bid;
    }

    public ProductCode getPair() {
        return pair;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public BigDecimal getBid() {
        return bid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spread spread = (Spread) o;
        return Objects.equals(pair, spread.pair) && Objects.equals(timestamp, spread.timestamp) && Objects.equals(ask, spread.ask) && Objects.equals(bid, spread.bid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pair, timestamp, ask, bid);
    }

    @Override
    public String toString() {
        return "Spread{" +
                "pair=" + pair +
                ", timestamp=" + timestamp +
                ", ask=" + ask +
                ", bid=" + bid +
                '}';
    }
}
