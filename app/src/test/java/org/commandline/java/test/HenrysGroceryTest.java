package org.commandline.java.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HenrysGroceryTest {

    @ParameterizedTest
    @ValueSource(strings = {"soup", "bread", "milk", "apples"})
    public void testAllAvailableStockItemsByName(String itemName) {
        //Arrange
        HenrysGrocery unit = new HenrysGrocery();
        //Act
        StockItem stockItem = unit.getStockItemByName(itemName);
        //Assert
        assertNotNull(stockItem);
    }
}
