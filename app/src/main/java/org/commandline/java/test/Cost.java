package org.commandline.java.test;

import java.math.BigDecimal;

public class Cost {
    private BigDecimal bigDecimalPrice;

    public Cost(String price) {
        this.bigDecimalPrice = new BigDecimal(price);
    }

    public String displayFormat() {
        return String.format("%.2f", this.bigDecimalPrice);
    }
}
