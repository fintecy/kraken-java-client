package org.fintecy.md.kraken.model;

public enum OrderType {
    MARKET("m"),
    LIMIT("l");

    private final String type;

    OrderType(String type) {
        this.type = type;
    }

    public static OrderType orderType(String type) {
        for (OrderType t : values()) {
            if (t.type.equals(type))
                return t;
        }
        return OrderType.MARKET;
    }

    public String getType() {
        return type;
    }

}
