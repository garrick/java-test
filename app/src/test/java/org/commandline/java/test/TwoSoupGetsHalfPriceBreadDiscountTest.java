package org.commandline.java.test;

import org.junit.jupiter.api.Test;

public class TwoSoupGetsHalfPriceBreadDiscountTest {

    @Test
    public void testDiscountApplies() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        Basket basket = new Basket();
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("bread"));
    }
}
