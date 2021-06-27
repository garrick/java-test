package org.commandline.java.test;

import java.math.BigDecimal;
import java.util.HashMap;

public class HenrysGrocery {
    private HashMap<String, StockItem> inventory = new HashMap<>();
    private UnitBuilder unitBuilder = new UnitBuilder();

    public HenrysGrocery() {
        inventory.put("soup", buildStockItem("soup", "tin", "0.65"));
        inventory.put("bread", buildStockItem("bread", "loaf", "0.80"));
    }

    public StockItem getStockItemByName(String itemName) {
        return inventory.get(itemName);
    }

    private StockItem buildStockItem(String productName, String unitName, String price) {
        return new StockItem(new Product(productName),
                unitBuilder.buildUnitByName(unitName),
                new Cost(new BigDecimal(price)));
    }
}
