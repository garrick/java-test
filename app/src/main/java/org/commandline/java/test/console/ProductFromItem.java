package org.commandline.java.test.console;

import java.util.HashMap;

public class ProductFromItem {
    private HashMap<String, String> itemToProductInventoryMap;
    public static final String PRODUCT_UNKNOWN = "PRODUCT_UNKNOWN";

    public ProductFromItem(HashMap<String, String> itemToProductInventoryMap) {
        this.itemToProductInventoryMap = itemToProductInventoryMap;
    }

    public String convertSelectionToProduct(String productNumber) {
        try{
            int converted = Integer.parseInt(productNumber);
            return this.itemToProductInventoryMap.getOrDefault(""+Math.abs(converted), PRODUCT_UNKNOWN);
        } catch (RuntimeException re){
        }
        return PRODUCT_UNKNOWN;
    }
}
