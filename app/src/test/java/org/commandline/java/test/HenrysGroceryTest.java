package org.commandline.java.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class HenrysGroceryTest {

    @ParameterizedTest
    @ValueSource(strings = {"soup", "bread", "milk", "apples"})
    public void testAllAvailableStockItemsByNameReturnsUniqueInstance(String itemName) {
        //Arrange
        HenrysGrocery unit = new HenrysGrocery();
        //Act
        StockItem stockItem1 = unit.getStockItemByName(itemName);
        StockItem stockItem2 = unit.getStockItemByName(itemName);
        //Assert
        assertEquals(itemName, stockItem1.productName());
        assertNotSame(stockItem1, stockItem2);
    }
}
