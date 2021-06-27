package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketTest {

    @Test
    public void testBasketCanAddItems() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket();
        //Act
        unit = unit.add(henrysGrocery.getStockItemByName("bread"));
        unit = unit.add(henrysGrocery.getStockItemByName("milk"));
        //Assert
        assertEquals(2, unit.itemCount());
    }

    @Test
    public void testBasketCanGiveTotalCostWithoutDiscounts() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket();
        //Act
        unit = unit.add(henrysGrocery.getStockItemByName("bread"));
        unit = unit.add(henrysGrocery.getStockItemByName("milk"));
        Cost expectedCost = new Cost("2.10");
        Cost actualCost = unit.totalCost();
        //Assert
        assertEquals(expectedCost, actualCost);
    }
}
