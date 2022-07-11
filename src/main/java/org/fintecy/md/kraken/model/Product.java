package org.fintecy.md.kraken.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class Product extends MicroType<String> {
    private final String altname;
    private final String wsname;
    private final String aclass_base;
    private final String base;
    private final String aclass_quote;
    private final String quote;
    private final String lot;
    private final int pair_decimals;
    private final int lot_decimals;
    private final int lot_multiplier;
    private final int[] leverage_buy;
    private final int[] leverage_sell;
    private final Map<Long, Double> fees;
    private final Map<Long, Double> fees_maker;
    private final String fee_volume_currency;
    private final int margin_call;
    private final int margin_stop;
    private final BigDecimal ordermin;

    public Product(String id, String altname, String wsname, String aclass_base, String base, String aclass_quote,
                   String quote, String lot, int pair_decimals, int lot_decimals, int lot_multiplier,
                   int[] leverage_buy, int[] leverage_sell, Map<Long, Double> fees, Map<Long, Double> fees_maker,
                   String fee_volume_currency, int margin_call, int margin_stop, BigDecimal ordermin) {
        super(id);
        this.altname = altname;
        this.wsname = wsname;
        this.aclass_base = aclass_base;
        this.base = base;
        this.aclass_quote = aclass_quote;
        this.quote = quote;
        this.lot = lot;
        this.pair_decimals = pair_decimals;
        this.lot_decimals = lot_decimals;
        this.lot_multiplier = lot_multiplier;
        this.leverage_buy = leverage_buy;
        this.leverage_sell = leverage_sell;
        this.fees = fees;
        this.fees_maker = fees_maker;
        this.fee_volume_currency = fee_volume_currency;
        this.margin_call = margin_call;
        this.margin_stop = margin_stop;
        this.ordermin = ordermin;
    }

    public String getAltname() {
        return altname;
    }

    public String getWsname() {
        return wsname;
    }

    public String getAclass_base() {
        return aclass_base;
    }

    public String getBase() {
        return base;
    }

    public String getAclass_quote() {
        return aclass_quote;
    }

    public String getQuote() {
        return quote;
    }

    public String getLot() {
        return lot;
    }

    public int getPair_decimals() {
        return pair_decimals;
    }

    public int getLot_decimals() {
        return lot_decimals;
    }

    public int getLot_multiplier() {
        return lot_multiplier;
    }

    public int[] getLeverage_buy() {
        return leverage_buy;
    }

    public int[] getLeverage_sell() {
        return leverage_sell;
    }

    public Map<Long, Double> getFees() {
        return fees;
    }

    public Map<Long, Double> getFees_maker() {
        return fees_maker;
    }

    public String getFee_volume_currency() {
        return fee_volume_currency;
    }

    public int getMargin_call() {
        return margin_call;
    }

    public int getMargin_stop() {
        return margin_stop;
    }

    public BigDecimal getOrdermin() {
        return ordermin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return pair_decimals == product.pair_decimals
                && lot_decimals == product.lot_decimals
                && lot_multiplier == product.lot_multiplier
                && margin_call == product.margin_call
                && margin_stop == product.margin_stop
                && Objects.equals(altname, product.altname)
                && Objects.equals(wsname, product.wsname)
                && Objects.equals(aclass_base, product.aclass_base)
                && Objects.equals(base, product.base)
                && Objects.equals(aclass_quote, product.aclass_quote)
                && Objects.equals(quote, product.quote)
                && Objects.equals(lot, product.lot)
                && Arrays.equals(leverage_buy, product.leverage_buy)
                && Arrays.equals(leverage_sell, product.leverage_sell)
                && Objects.equals(fees, product.fees)
                && Objects.equals(fees_maker, product.fees_maker)
                && Objects.equals(fee_volume_currency, product.fee_volume_currency)
                && Objects.equals(ordermin, product.ordermin);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), altname, wsname, aclass_base, base, aclass_quote, quote, lot,
                pair_decimals, lot_decimals, lot_multiplier, fees, fees_maker, fee_volume_currency, margin_call,
                margin_stop, ordermin);
        result = 31 * result + Arrays.hashCode(leverage_buy);
        result = 31 * result + Arrays.hashCode(leverage_sell);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + getValue() + '\'' +
                ", altname='" + altname + '\'' +
                ", wsname='" + wsname + '\'' +
                ", aclass_base='" + aclass_base + '\'' +
                ", base='" + base + '\'' +
                ", aclass_quote='" + aclass_quote + '\'' +
                ", quote='" + quote + '\'' +
                ", lot='" + lot + '\'' +
                ", pair_decimals=" + pair_decimals +
                ", lot_decimals=" + lot_decimals +
                ", lot_multiplier=" + lot_multiplier +
                ", leverage_buy=" + Arrays.toString(leverage_buy) +
                ", leverage_sell=" + Arrays.toString(leverage_sell) +
                ", fees=" + fees +
                ", fees_maker=" + fees_maker +
                ", fee_volume_currency='" + fee_volume_currency + '\'' +
                ", margin_call=" + margin_call +
                ", margin_stop=" + margin_stop +
                ", ordermin=" + ordermin +
                "} ";
    }
}
