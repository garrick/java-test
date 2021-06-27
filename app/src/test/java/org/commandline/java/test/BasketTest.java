package org.commandline.java.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testBasketCanGiveTotalCostWithoutDiscounts() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket(LocalDateTime.now());
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

    //ALL TESTS DEFINED in README.MD for acceptance
    @Test
    public void testThreeSoupTwoLoavesIsThreeAndFifteen() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket(LocalDateTime.now());
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

    @Test
    public void testThreeSoupTwoLoavesIsThreeAndFiftyFiveWithoutDiscountInDateRange() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket(LocalDateTime.now().plusDays(7));
        //Act
        unit = unit.add(henrysGrocery.getStockItemByName("soup"));
        unit = unit.add(henrysGrocery.getStockItemByName("soup"));
        unit = unit.add(henrysGrocery.getStockItemByName("soup"));
        unit = unit.add(henrysGrocery.getStockItemByName("bread"));
        unit = unit.add(henrysGrocery.getStockItemByName("bread"));
        unit = unit.applyDiscount(new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery));
        Cost expectedCost = new Cost("3.55");
        Cost actualCost = unit.totalCost();
        //Assert
        assertEquals(expectedCost.asBigDecimal(), actualCost.asBigDecimal());
    }

    @Disabled("Need TenPercentOffApples Discount")
    @Test
    public void testSixApplesBottleOfMilkBoughtTodayIsOneAndNinty() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket(LocalDateTime.now().plusDays(3));
        //Act
        unit = unit.add(henrysGrocery.getStockItemByName("apple"));
        unit = unit.add(henrysGrocery.getStockItemByName("apple"));
        unit = unit.add(henrysGrocery.getStockItemByName("apple"));
        unit = unit.add(henrysGrocery.getStockItemByName("apple"));
        unit = unit.add(henrysGrocery.getStockItemByName("apple"));
        unit = unit.add(henrysGrocery.getStockItemByName("apple"));
        unit = unit.add(henrysGrocery.getStockItemByName("milk"));
        //unit = unit.applyDiscount(new TenPercentOffApples(henrysGrocery));
        Cost expectedCost = new Cost("1.90");
        Cost actualCost = unit.totalCost();
        //Assert
        assertEquals(expectedCost.asBigDecimal(), actualCost.asBigDecimal());
    }
}
