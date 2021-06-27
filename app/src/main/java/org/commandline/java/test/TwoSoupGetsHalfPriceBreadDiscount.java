package org.commandline.java.test;

public class TwoSoupGetsHalfPriceBreadDiscount implements Discountable {

    public DiscountItem check(Basket basket) {
        return DiscountItem.NONE;
    }
}
