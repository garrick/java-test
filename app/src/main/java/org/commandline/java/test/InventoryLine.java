package org.commandline.java.test;

public class InventoryLine {
    private String productName;
    private final String unitName;
    private final String cost;

    public InventoryLine(String productName, String unitName, String cost) {
        this.productName = productName;
        this.unitName = unitName;
        this.cost = cost;
    }

    public String getProductName() {
        return productName;
    }

    public String getUnitType() {
        return this.unitName;
    }

    public String getCost() {
        return this.cost;
    }
}
