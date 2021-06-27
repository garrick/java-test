package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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
    public void testBasketCanGivetotalCostWithoutDiscounts() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket(LocalDateTime.now());
        //Act
        unit = unit.add(henrysGrocery.getStockItemByName("bread"));
        unit = unit.add(henrysGrocery.getStockItemByName("milk"));
        BigDecimal expectedBigDecimal = new BigDecimal("2.10");
        BigDecimal actualBigDecimal = unit.totalCost();
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
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
        BigDecimal expectedBigDecimal = new BigDecimal("3.15");
        BigDecimal actualBigDecimal = unit.totalCost();
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
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
        BigDecimal expectedBigDecimal = new BigDecimal("3.55");
        BigDecimal actualBigDecimal = unit.totalCost();
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
    }

    @Test
    public void testSixApplesBottleOfMilkBoughtTodayIsOneAndNintyToday() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket(LocalDateTime.now());
        //Act
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("milk"));
        unit = unit.applyDiscount(new TenPercentOffApples(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("1.90");
        BigDecimal actualBigDecimal = unit.totalCost();
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
    }

    @Test
    public void testSixApplesBottleOfMilkBoughtTodayIsOneAndEightyFourIn5Days() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket(LocalDateTime.now().plusDays(5));
        //Act
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("milk"));
        unit = unit.applyDiscount(new TenPercentOffApples(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("1.84");
        BigDecimal actualBigDecimal = unit.totalCost();
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
    }

    @Test
    public void testThreeApplesTwoSoupALoafOfBreadBoughtTodayIsOneAndEightyFourIn5Days() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket unit = new Basket(LocalDateTime.now().plusDays(5));
        //Act
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("apples"));
        unit = unit.add(henrysGrocery.getStockItemByName("soup"));
        unit = unit.add(henrysGrocery.getStockItemByName("soup"));
        unit = unit.add(henrysGrocery.getStockItemByName("bread"));
        unit = unit.applyDiscount(new TenPercentOffApples(henrysGrocery));
        unit = unit.applyDiscount(new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("1.97");
        BigDecimal actualBigDecimal = unit.totalCost();
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
    }
}
