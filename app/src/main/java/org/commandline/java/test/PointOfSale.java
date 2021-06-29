package org.commandline.java.test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PointOfSale {

    public BigDecimal totalCost(ArrayList<StockItem> items, Discounts discounts) {
        var ref = new Object() {
            BigDecimal sum = new BigDecimal("0.00");
        };
        items.forEach((c) -> ref.sum = c.cost().add(ref.sum));
        var discountRef = new Object() {
            BigDecimal discountSum = new BigDecimal("0.00");
        };
        discounts.allDiscounts().forEach((d) -> discountRef.discountSum = d.discountAmount().add(discountRef.discountSum));
        return ref.sum.subtract(discountRef.discountSum);
    }
}
