package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketTest {

    @Test
    public void testBasketCanAddItems() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket(LocalDateTime.now());
        //Act
        unit = unit.add(henrysGrocery.getStockItemByName("bread"));
        unit = unit.add(henrysGrocery.getStockItemByName("milk"));
        //Assert
        assertEquals(2, unit.itemCount());
    }

    @Test
    public void testCountProductByNameHasTwoOfEachProduct() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        List<String> validProductNames = Arrays.asList("soup", "soup", "soup", "bread", "bread", "milk");
        Basket unit = new Basket(LocalDateTime.now());
        for (String productName: validProductNames) {
            unit = unit.add(henrysGrocery.getStockItemByName(productName));
        }
        //Act & Assert
        assertEquals(3, unit.countProductByName("soup"));
        assertEquals(2, unit.countProductByName("bread"));
        assertEquals(1, unit.countProductByName("milk"));
        assertEquals(0, unit.countProductByName("apples"));
    }

    @Test
    public void testDescribeForShopperGivesContentsOfBasket(){
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket(LocalDateTime.now());
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("soup"));
        unit = unit.add(henrysGrocery.getStockItemByName("soup"));
        unit = unit.add(henrysGrocery.getStockItemByName("bread"));
        //Act
        String basketDescription = unit.describeForShopper();
        //Assert
        assertTrue(basketDescription.contains("apples=3"));
        assertTrue(basketDescription.contains("soup=2"));
        assertTrue(basketDescription.contains("bread=1"));
    }

    @Test
    public void testDescribeNicelyDescribesAnEmptyBasket() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket(LocalDateTime.now());
        //Act
        String basketDescription = unit.describeForShopper();
        //Assert
        assertEquals("Your shopping basket is empty.", basketDescription);
    }
}
