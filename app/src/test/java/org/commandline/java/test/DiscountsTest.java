package org.commandline.java.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountsTest {

    private Discounts unit;

    @BeforeEach
    public void setUp(){
        unit = new Discounts();
    }
    
    @Test
    public void testThreeSoupTwoLoavesIsFortyCentsOff() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket basket = new Basket(LocalDateTime.now());
        //Act
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("bread"));
        basket = basket.add(henrysGrocery.getStockItemByName("bread"));
        unit = unit.applyDiscount(basket, new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("0.40");
        BigDecimal actualBigDecimal = unit.totalDiscount();
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
        unit = unit.applyDiscount(basket, new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("0.00");
        BigDecimal actualBigDecimal = unit.totalDiscount();
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
        unit = unit.applyDiscount(basket, new TenPercentOffApples(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("0.00");
        BigDecimal actualBigDecimal = unit.totalDiscount();
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
        unit = unit.applyDiscount(basket, new TenPercentOffApples(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("0.06");
        BigDecimal actualBigDecimal = unit.totalDiscount();
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
        unit = unit.applyDiscount(basket, new TenPercentOffApples(henrysGrocery));
        unit = unit.applyDiscount(basket, new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery));
        BigDecimal expectedBigDecimal = new BigDecimal("0.43");
        BigDecimal actualBigDecimal = unit.totalDiscount();
        //Assert
        assertEquals(expectedBigDecimal, actualBigDecimal);
    }
}
