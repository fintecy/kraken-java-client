package org.fintecy.md.kraken.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Candle extends MicroType<ProductCode> {
    private final Instant time;
    private final BigDecimal open;
    private final BigDecimal high;
    private final BigDecimal low;
    private final BigDecimal close;
    private final BigDecimal vwap;
    private final BigDecimal volume;
    private final int count;

    public Candle(ProductCode ticker, Instant time, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close,
                  BigDecimal vwap, BigDecimal volume, int count) {
        super(ticker);
        this.time = time;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.vwap = vwap;
        this.volume = volume;
        this.count = count;
    }

    public Instant getTime() {
        return time;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public BigDecimal getVwap() {
        return vwap;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Candle candle = (Candle) o;
        return count == candle.count && Objects.equals(time, candle.time) && Objects.equals(open, candle.open) && Objects.equals(high, candle.high) && Objects.equals(low, candle.low) && Objects.equals(close, candle.close) && Objects.equals(vwap, candle.vwap) && Objects.equals(volume, candle.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), time, open, high, low, close, vwap, volume, count);
    }

    @Override
    public String toString() {
        return "Candle{" +
                "pair=" + getValue().getCode() +
                ", time=" + time +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", vwap=" + vwap +
                ", volume=" + volume +
                ", count=" + count +
                "}";
    }
}
