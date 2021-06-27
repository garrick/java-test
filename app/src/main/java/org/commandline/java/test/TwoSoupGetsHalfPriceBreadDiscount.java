package org.commandline.java.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TwoSoupGetsHalfPriceBreadDiscount implements Discountable {

    private HenrysGrocery henrysGrocery;

    public TwoSoupGetsHalfPriceBreadDiscount(HenrysGrocery henrysGrocery) {
        this.henrysGrocery = henrysGrocery;
    }

    public DiscountItem check(Basket basket) {
        if (!isCurrentlyAvailable(basket.shoppingTime())) return DiscountItem.NONE;
        if(2 <= basket.countProductByName("soup") &&
        1 <= basket.countProductByName("bread")) {
            BigDecimal breadCost = henrysGrocery.getStockItemByName("bread").cost();
            return new DiscountItem("Half price bread for two soups",
                    breadCost.divide(new BigDecimal("2")));
        }
        return DiscountItem.NONE;
    }

    @Override
    public boolean isCurrentlyAvailable(LocalDateTime localDateTime) {
        LocalDateTime validFrom = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).minusDays(1);
        LocalDateTime validThrough = validFrom.plusDays(7);
        return localDateTime.isAfter(validFrom) && localDateTime.isBefore(validThrough);
    }
}
