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
        unit.add(henrysGrocery.getStockItemByName("bread"));
        unit.add(henrysGrocery.getStockItemByName("milk"));
        //Assert
        assertEquals(2, unit.itemCount());
    }
}
