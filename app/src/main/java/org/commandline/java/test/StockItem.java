package org.commandline.java.test;

import java.math.BigDecimal;

public class StockItem {
    private Product product;
    private Unit unit;
    private BigDecimal cost;

    public StockItem(Product product, Unit unit, BigDecimal cost) {
        if(Unit.UNKNOWN == unit) throw new IllegalArgumentException("Unknown Unit type");
        this.product = product;
        this.unit = unit;
        this.cost = cost;
    }

    public String productName() {
        return this.product.displayName();
    }

    public BigDecimal cost() {
        return this.cost.add(new BigDecimal("0.00"));
    }
}
