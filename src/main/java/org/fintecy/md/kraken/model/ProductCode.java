package org.fintecy.md.kraken.model;

public class ProductCode extends MicroType<String> implements Comparable<ProductCode> {
    public ProductCode(String value) {
        super(value);
    }

    public static ProductCode product(String code) {
        return new ProductCode(code);
    }

    public String getCode() {
        return value;
    }

    public CurrencyCode getFrom() {
        return CurrencyCode.currency(value.split("-")[0]);
    }

    public CurrencyCode getTo() {
        return CurrencyCode.currency(value.split("-")[1]);
    }

    @Override
    public int compareTo(ProductCode o) {
        return value.compareTo(o.value);
    }
}
