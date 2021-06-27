package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoSoupGetsHalfPriceBreadDiscountTest {

    //TODO: Complete test
    @Test
    public void testDiscountDoesNotApply() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket basket = new Basket();
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        //basket = basket.add(henrysGrocery.getStockItemByName("bread"));
        //Act
        basket = basket.applyDiscount(new TwoSoupGetsHalfPriceBreadDiscount());
        //Assert
        assertEquals(0, basket.discountsCount());
    }
}
