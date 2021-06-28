package org.commandline.java.test.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductFromItemTest {

    private ProductFromItem unit;
    private HashMap<String,String> itemToProductInventoryMap;

    @BeforeEach
    public void setUp(){
        itemToProductInventoryMap = new HashMap<>();
        itemToProductInventoryMap.put("1", "soup");
        unit = new ProductFromItem(itemToProductInventoryMap);
    }
    @Test
    public void convertSelectionIntOneToStringSoup() {
        //Act
        String productName = unit.convertSelectionToProduct("1");
        //Assert
        assertEquals("soup", productName);
    }

    @Test
    public void convertSelectionIntNegativeOneToStringSoup() {
        //Act
        String productName = unit.convertSelectionToProduct("-1");
        //Assert
        assertEquals("soup", productName);
    }

    @Test
    public void convertSelectionDefaultsLookupToProductUnknown(){
        //Act
        String productName = unit.convertSelectionToProduct("1138");
        //Assert
        assertEquals("PRODUCT_UNKNOWN", productName);
    }

    @Test
    public void convertSelectionHandlesInvalidIdsWithProductUnknown(){
        //Act
        String productName = unit.convertSelectionToProduct("THX-1138");
        //Assert
        assertEquals("PRODUCT_UNKNOWN", productName);
    }
}
