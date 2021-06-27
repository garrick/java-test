package org.commandline.java.test;

import java.math.BigDecimal;
import java.util.Objects;

public class Cost {
    private BigDecimal bigDecimalPrice;

    public Cost(String price) {
        this.bigDecimalPrice = new BigDecimal(price);
    }

    public String displayFormat() {
        return String.format("%.2f", this.bigDecimalPrice);
    }

    public BigDecimal asBigDecimal() {
        return this.bigDecimalPrice;
    }

    @Override
    public String toString() {
        return displayFormat();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cost cost = (Cost) o;
        return bigDecimalPrice.equals(cost.bigDecimalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bigDecimalPrice);
    }
}
