package org.fintecy.md.kraken.model;

public enum Interval {
    MINUTE(1),
    MIN_5(5),
    MIN_15(15),
    MIN_30(30),
    HOUR(60),
    HOUR_4(240),
    DAY(1440),
    WEEK(10080),
    DAY_15(21600);

    private final int min;

    Interval(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }
}
