package org.commandline.java.test;

import org.commandline.java.test.exceptions.InvalidInventoryItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testGettingStockItemByNameThrowsInvalidArgumentException(){
        //Arrange
        HenrysGrocery unit = new HenrysGrocery();
        //Act && Assert
        assertThrows(InvalidInventoryItem.class, () -> {
            unit.getStockItemByName("durian"); //This should not be available ANYWHERE! :)
        });
    }
}
