package org.commandline.java.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TwoSoupGetsHalfPriceBreadDiscountTest {

    private TwoSoupGetsHalfPriceBreadDiscount unit;
    private Basket basket;
    private HenrysGrocery henrysGrocery;

    @BeforeEach
    public void setUp() {
        //Arrange
        henrysGrocery = new HenrysGrocery();
        basket = new Basket(LocalDateTime.now());
        unit = new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery);
    }

    @Test
    public void testDiscountDoesNotApply() {
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        TwoSoupGetsHalfPriceBreadDiscount unit = new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery);
        //Act
        DiscountItem discountItem = unit.check(basket);
        //Assert
        assertSame(DiscountItem.NONE, discountItem);
    }

    @Test
    public void testDiscountApplies() {
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("bread"));
        //Act
        DiscountItem discountItem = unit.check(basket);
        //Assert
        BigDecimal expectedDiscount = new BigDecimal("0.40");
        assertNotSame(DiscountItem.NONE, discountItem);
        assertEquals(expectedDiscount, discountItem.discountAmount());
        assertEquals("Half price bread for two soups", discountItem.description());
    }

    @Test
    public void testIsCurrentlyAvailableIsTrueForToday() {
        //Arrange
        LocalDateTime now = LocalDateTime.now();
        //Act
        boolean isAvailable = unit.isCurrentlyAvailable(now);
        //Assert
        assertTrue(isAvailable);
    }
    @Test
    public void testIsCurrentlyAvailableIsFalseAWeekFromToday() {
        //Arrange
        LocalDateTime now = LocalDateTime.now().plusDays(7);
        //Act
        boolean isAvailable = unit.isCurrentlyAvailable(now);
        //Assert
        assertFalse(isAvailable);
    }

}
