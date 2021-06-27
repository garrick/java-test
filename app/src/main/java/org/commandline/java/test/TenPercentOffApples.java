package org.commandline.java.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class TenPercentOffApples implements Discountable {
    private final HenrysGrocery henrysGrocery;

    public TenPercentOffApples(HenrysGrocery henrysGrocery) {
        this.henrysGrocery = henrysGrocery;
    }

    @Override
    public DiscountItem check(Basket basket) {
        if(!isCurrentlyAvailable(basket.shoppingTime())) return DiscountItem.NONE;
        long apples = basket.countProductByName("apples");
        if(apples == 0 ) return DiscountItem.NONE;
        BigDecimal applePrice = henrysGrocery.getStockItemByName("apples").cost();
        BigDecimal appleDiscount = applePrice.multiply(BigDecimal.valueOf(0.1));
        return new DiscountItem("10% off them apples",
                appleDiscount.multiply(BigDecimal.valueOf(apples)).stripTrailingZeros());
    }

    @Override
    public boolean isCurrentlyAvailable(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime validFrom = now.truncatedTo(ChronoUnit.DAYS).plusDays(3);
        Month thisMonth = now.getMonth();
        int thisMonthLength = thisMonth.length(now.getChronology().isLeapYear(now.getYear()));
        int daysLeftThisMonth = thisMonthLength - now.getDayOfMonth();
        int nextMonthLength = thisMonth.plus(1).length(validFrom.getChronology().isLeapYear(validFrom.getYear()));
        LocalDateTime validThrough = now.plusDays(daysLeftThisMonth + nextMonthLength);
        return localDateTime.isAfter(validFrom) && localDateTime.isBefore(validThrough);
    }
}
