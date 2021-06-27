package org.commandline.java.test;

import java.math.BigDecimal;
import java.util.HashMap;

public class HenrysGrocery {
    private HashMap<String, StockItem> inventory = new HashMap<>();
    private UnitBuilder unitBuilder = new UnitBuilder();

    public HenrysGrocery() {
        inventory.put("soup", new StockItem(new Product("soup"),
                unitBuilder.buildUnitByName("tin"),
                new Cost(new BigDecimal("0.65"))));
    }

    public StockItem getStockItemByName(String itemName) {
        return inventory.get(itemName);
    }
}
