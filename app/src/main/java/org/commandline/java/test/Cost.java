package org.commandline.java.test;

import java.math.BigDecimal;

public class Cost {
    private BigDecimal bigDecimalValue;

    public Cost(BigDecimal value) {
        this.bigDecimalValue = value;
    }

    public String displayFormat() {
        return String.format("%.2f", this.bigDecimalValue);
    }
}
