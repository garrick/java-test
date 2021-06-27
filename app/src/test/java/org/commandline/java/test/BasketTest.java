package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testCountProductByNameHasTwoOfEachProduct() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        List<String> validProductNames = Arrays.asList("soup", "soup", "soup", "bread", "bread", "milk");
        Basket unit = new Basket();
        for (String productName: validProductNames) {
            unit = unit.add(henrysGrocery.getStockItemByName(productName));
        }
        //Act & Assert
        assertEquals(3, unit.countProductByName("soup"));
        assertEquals(2, unit.countProductByName("bread"));
        assertEquals(1, unit.countProductByName("milk"));
        assertEquals(0, unit.countProductByName("apples"));
    }

    //ALL TESTS DEFINED in README.MD for acceptance
    @Test
    public void testThreeSoupTwoLoavesIsThreeAndFifteen() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket();
        //Act
        unit = unit.add(henrysGrocery.getStockItemByName("soup"));
        unit = unit.add(henrysGrocery.getStockItemByName("soup"));
        unit = unit.add(henrysGrocery.getStockItemByName("soup"));
        unit = unit.add(henrysGrocery.getStockItemByName("bread"));
        unit = unit.add(henrysGrocery.getStockItemByName("bread"));
        unit = unit.applyDiscount(new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery));
        Cost expectedCost = new Cost("3.15");
        Cost actualCost = unit.totalCost();
        //Assert
        assertEquals(expectedCost.asBigDecimal(), actualCost.asBigDecimal());
    }
}
