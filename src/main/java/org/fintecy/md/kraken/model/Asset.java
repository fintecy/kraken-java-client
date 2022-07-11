package org.fintecy.md.kraken.model;

import java.util.Objects;

public class Asset extends MicroType<String> implements Comparable<Asset> {
    private final String name;
    private final String type;
    private final int decimals;
    private final int displayDecimals;

    public Asset(String id, String name, String type, int decimals, int displayDecimals) {
        super(id);
        this.name = name;
        this.type = type;
        this.decimals = decimals;
        this.displayDecimals = displayDecimals;
    }

    public String getCode() {
        return value;
    }

    @Override
    public int compareTo(Asset o) {
        return value.compareTo(o.value);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDecimals() {
        return decimals;
    }

    public int getDisplayDecimals() {
        return displayDecimals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Asset asset = (Asset) o;
        return decimals == asset.decimals
                && displayDecimals == asset.displayDecimals
                && Objects.equals(name, asset.name)
                && Objects.equals(type, asset.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, type, decimals, displayDecimals);
    }

    @Override
    public String toString() {
        return "Asset{" +
                "code='" + getValue() + '\'' +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", decimals=" + decimals +
                ", displayDecimals=" + displayDecimals +
                "}";
    }
}
