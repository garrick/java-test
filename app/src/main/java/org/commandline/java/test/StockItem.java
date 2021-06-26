package org.commandline.java.test;

public class StockItem {
    private Product product;
    private Unit unit;
    private Cost cost;

    public StockItem(Product product, Unit unit, Cost cost) {
        this.product = product;
        this.unit = unit;
        this.cost = cost;
    }
}
