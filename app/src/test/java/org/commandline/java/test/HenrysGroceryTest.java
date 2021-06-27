package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HenrysGroceryTest {

    @Test
    public void testAllAvailableStockItemsByName() {
        //Arrange
        HenrysGrocery unit = new HenrysGrocery();
        //Act
        StockItem stockItem = unit.getStockItemByName("soup");
        //Assert
        assertNotNull(stockItem);
    }
}
