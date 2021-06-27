package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TwoSoupGetsHalfPriceBreadDiscountTest {

    @Test
    public void testDiscountDoesNotApply() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket basket = new Basket();
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
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket basket = new Basket();
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("bread"));
        TwoSoupGetsHalfPriceBreadDiscount unit = new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery);
        //Act
        DiscountItem discountItem = unit.check(basket);
        //Assert
        BigDecimal expectedDiscount = new BigDecimal("0.40");
        assertNotSame(DiscountItem.NONE, discountItem);
        assertEquals(expectedDiscount, discountItem.discountAmount());
        assertEquals("Half price bread for two soups", discountItem.description());
    }
}
