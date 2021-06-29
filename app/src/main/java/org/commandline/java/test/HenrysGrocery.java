package org.commandline.java.test;

import org.commandline.java.test.console.ProductFromItem;
import org.commandline.java.test.exceptions.InvalidInventoryItem;

import java.math.BigDecimal;
import java.util.HashMap;

public class HenrysGrocery {
    private HashMap<String, InventoryLine> inventory = new HashMap<>();
    private UnitBuilder unitBuilder = new UnitBuilder();
    private ProductFromItem productFromItem;
    private String inventoryMessage;

    public HenrysGrocery() {
        inventory.put("soup", new InventoryLine("soup", "tin", "0.65"));
        inventory.put("bread", new InventoryLine("bread", "loaf", "0.80"));
        inventory.put("milk", new InventoryLine("milk", "bottle", "1.30"));
        inventory.put("apples", new InventoryLine("apples", "single", "0.10"));
        buildInventoryData();
    }

    public StockItem getStockItemByName(String itemName) {
        if( !inventory.containsKey(itemName)) throw new InvalidInventoryItem();
        InventoryLine inventoryLine = inventory.get(itemName);
        return buildStockItem(inventoryLine.getProductName(),
                inventoryLine.getUnitType(),
                inventoryLine.getCost());
    }

    private StockItem buildStockItem(String productName, String unitName, String price) {
        return new StockItem(new Product(productName),
                unitBuilder.buildUnitByName(unitName),
                new BigDecimal(price));
    }

    public String getInventoryAsCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product,Unit,Cost\n");
        inventory.values().forEach(v -> sb.append(String.format("%s,%s,%s\n",
                v.getProductName(),v.getUnitType(), v.getCost())));
        return sb.toString();
    }

    private void buildInventoryData() {
        StringBuilder sb = new StringBuilder();
        String inventoryCSV = getInventoryAsCSV();
        HashMap<String,String> itemIdToProductHashMap = new HashMap<>();
        String[] lines = inventoryCSV.split("\n");
        sb.append(String.format("\t Item# \t| %-7s \t| %-5s \t| %-5s \n", (Object[]) lines[0].split(",")));
        for (int i = 1; i < lines.length; i++) {
            Object[] columns = (Object[]) lines[i].split(",");
            sb.append(String.format(" \t" + i + " \t| %-7s \t| %-5s \t| %-5s \n", columns));
            itemIdToProductHashMap.put("" + i, "" + columns[0]);
        }
        productFromItem = new ProductFromItem(itemIdToProductHashMap);
        inventoryMessage = sb.toString();
    }

    public String getInventoryMessage() {
        return inventoryMessage;
    }

    public String productFromItemConvert(String itemValue) {
        return productFromItem.convertSelectionToProduct(itemValue);
    }
}
