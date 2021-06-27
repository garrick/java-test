package org.commandline.java.test;

import java.math.BigDecimal;

public class TwoSoupGetsHalfPriceBreadDiscount implements Discountable {

    private HenrysGrocery henrysGrocery;

    public TwoSoupGetsHalfPriceBreadDiscount(HenrysGrocery henrysGrocery) {
        this.henrysGrocery = henrysGrocery;
    }

    public DiscountItem check(Basket basket) {
        if(2 == basket.countProductByName("soup") &&
        1 == basket.countProductByName("bread")) {
            BigDecimal breadCost = henrysGrocery.getStockItemByName("bread").costAsBigDecimal();
            return new DiscountItem("Half price bread for two soups",
                    breadCost.divide(new BigDecimal("2")));
        }
        return DiscountItem.NONE;
    }
}
