package org.commandline.java.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointOfSaleTest {
    
    private PointOfSale unit;
    private Discounts discounts;
    @BeforeEach
    public void setUp(){
        unit = new PointOfSale();
        discounts = new Discounts();
    }

    @Test
    public void testBasketCanGivetotalCostWithoutDiscounts() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket basket = new Basket(LocalDateTime.now());
        //Act
        basket = basket.add(henrysGrocery.getStockItemByName("bread"));
        basket = basket.add(henrysGrocery.getStockItemByName("milk"));
        BigDecimal expectedBigDecimal = new BigDecimal("2.10");
        BigDecimal actualBigDecimal = unit.totalCost(basket.contents(), discounts);
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
    }
    //ALL TESTS DEFINED in README.MD for acceptance
    @Test
    public void testThreeSoupTwoLoavesIsThreeAndFifteen() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket basket = new Basket(LocalDateTime.now());
        //Act
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("bread"));
        basket = basket.add(henrysGrocery.getStockItemByName("bread"));
        discounts = discounts.applyDiscount(basket, new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("3.15");
        BigDecimal actualBigDecimal = unit.totalCost(basket.contents(), discounts);
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
    }

    @Test
    public void testThreeSoupTwoLoavesIsThreeAndFiftyFiveWithoutDiscountInDateRange() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket basket = new Basket(LocalDateTime.now().plusDays(7));
        //Act
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("bread"));
        basket = basket.add(henrysGrocery.getStockItemByName("bread"));
        discounts = discounts.applyDiscount(basket, new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("3.55");
        BigDecimal actualBigDecimal = unit.totalCost(basket.contents(), discounts);
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
    }

    @Test
    public void testSixApplesBottleOfMilkBoughtTodayIsOneAndNintyToday() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket basket = new Basket(LocalDateTime.now());
        //Act
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("milk"));
        discounts = discounts.applyDiscount(basket, new TenPercentOffApples(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("1.90");
        BigDecimal actualBigDecimal = unit.totalCost(basket.contents(), discounts);
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
    }

    @Test
    public void testSixApplesBottleOfMilkBoughtTodayIsOneAndEightyFourIn5Days() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket basket = new Basket(LocalDateTime.now().plusDays(5));
        //Act
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("milk"));
        discounts = discounts.applyDiscount(basket, new TenPercentOffApples(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("1.84");
        BigDecimal actualBigDecimal = unit.totalCost(basket.contents(), discounts);
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
    }

    @Test
    public void testThreeApplesTwoSoupALoafOfBreadBoughtTodayIsOneAndEightyFourIn5Days() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket basket = new Basket(LocalDateTime.now().plusDays(5));
        //Act
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("apples"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("bread"));
        discounts = discounts.applyDiscount(basket, new TenPercentOffApples(henrysGrocery));
        discounts = discounts.applyDiscount(basket, new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("1.97");
        BigDecimal actualBigDecimal = unit.totalCost(basket.contents(), discounts);
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
    }

}
