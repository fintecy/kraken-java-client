package org.fintecy.md.kraken.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class ExchangeRate {
    private final ProductCode pair;
    private final Instant timestamp;
    private final PriceLevel ask;
    private final PriceLevel bid;
    private final BigDecimal closed;
    private final BigDecimal volume;
    private final BigDecimal vwap;
    private final long tradeCount;
    private final BigDecimal low;
    private final BigDecimal high;
    private final BigDecimal open;

    public ExchangeRate(ProductCode pair, Instant timestamp, PriceLevel ask, PriceLevel bid, BigDecimal closed,
                        BigDecimal volume, BigDecimal vwap, long tradeCount, BigDecimal low, BigDecimal high, BigDecimal open) {
        this.pair = pair;
        this.timestamp = timestamp;
        this.ask = ask;
        this.bid = bid;
        this.closed = closed;
        this.volume = volume;
        this.vwap = vwap;
        this.tradeCount = tradeCount;
        this.low = low;
        this.high = high;
        this.open = open;
    }

    public ProductCode getPair() {
        return pair;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public PriceLevel getAsk() {
        return ask;
    }

    public PriceLevel getBid() {
        return bid;
    }

    public BigDecimal getClosed() {
        return closed;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public BigDecimal getVwap() {
        return vwap;
    }

    public long getTradeCount() {
        return tradeCount;
    }

    public BigDecimal getLow() {
        return low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public BigDecimal getOpen() {
        return open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate that = (ExchangeRate) o;
        return tradeCount == that.tradeCount
                && Objects.equals(pair, that.pair)
                && Objects.equals(timestamp, that.timestamp)
                && Objects.equals(ask, that.ask)
                && Objects.equals(bid, that.bid)
                && Objects.equals(closed, that.closed)
                && Objects.equals(volume, that.volume)
                && Objects.equals(vwap, that.vwap)
                && Objects.equals(low, that.low)
                && Objects.equals(high, that.high)
                && Objects.equals(open, that.open);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pair, timestamp, ask, bid, closed, volume, vwap, tradeCount, low, high, open);
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "pair=" + pair +
                ", timestamp=" + timestamp +
                ", ask=" + ask +
                ", bid=" + bid +
                ", closed=" + closed +
                ", volume=" + volume +
                ", vwap=" + vwap +
                ", tradeCount=" + tradeCount +
                ", low=" + low +
                ", high=" + high +
                ", open=" + open +
                '}';
    }
}
