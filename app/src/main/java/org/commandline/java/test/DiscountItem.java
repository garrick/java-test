package org.commandline.java.test;

import java.math.BigDecimal;

public class DiscountItem {
    public static DiscountItem NONE = new DiscountItem("Nothing", new BigDecimal("0.00"));
    private final String description;
    private final BigDecimal discount;

    public DiscountItem(String description, BigDecimal discount) {
        this.description = description;
        this.discount = discount;
    }

    public BigDecimal discountAmount(){
       return this.discount;
    }

    public String description() {
        return description;
    }
}
