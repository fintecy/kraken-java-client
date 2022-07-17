package org.fintecy.md.kraken.model;

public enum Side {
    BUY("b"),
    SELL("s");

    private final String side;

    Side(String side) {
        this.side = side;
    }

    public static Side side(String v) {
        if (BUY.side.equals(v))
            return BUY;
        else if (SELL.side.equals(v))
            return SELL;
        else
            throw new IllegalArgumentException("No such side: " + v);
    }

    public String getSide() {
        return side;
    }

}
