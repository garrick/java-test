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
            unit.getStockItemByName("asparagus"); //This should not be available ANYWHERE! :)
        });
    }

    @Test
    public void testGetInventoryAsCSV() {
        //Arrange
        HenrysGrocery unit = new HenrysGrocery();
        //Act
        String inventoryLinesCsv = unit.getInventoryAsCSV();
        //Assert
        assertTrue(inventoryLinesCsv.contains("Product,Unit,Cost\n"));
        assertTrue(inventoryLinesCsv.contains("soup,tin,0.65\n"));
        assertTrue(inventoryLinesCsv.contains("bread,loaf,0.80\n"));
        assertTrue(inventoryLinesCsv.contains("milk,bottle,1.30\n"));
        assertTrue(inventoryLinesCsv.contains("apples,single,0.10\n"));
    }


}
