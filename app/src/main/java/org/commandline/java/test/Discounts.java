package org.commandline.java.test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Discounts {

    private ArrayList<DiscountItem> discountItems;


    public Discounts() {
        this.discountItems = new ArrayList<>();
    }

    public Discounts(ArrayList<DiscountItem> discountItems) {
        this.discountItems = discountItems;
    }

    public Discounts applyDiscount(Basket basket, Discountable discount) {
        DiscountItem discountItem = discount.check(basket);
        if (discountItem != DiscountItem.NONE) {
            ArrayList<DiscountItem> ourDiscountItems = (ArrayList<DiscountItem>) this.discountItems.clone();
            ourDiscountItems.add(discountItem);
            return new Discounts(ourDiscountItems);
        }
        return new Discounts((ArrayList<DiscountItem>) this.discountItems.clone());
    }

    public BigDecimal totalDiscount() {
        var discountRef = new Object() {
            BigDecimal discountSum = new BigDecimal("0.00");
        };
        discountItems.forEach((d) -> discountRef.discountSum = d.discountAmount().add(discountRef.discountSum));
        return discountRef.discountSum;
    }

    public ArrayList<DiscountItem> allDiscounts() {
        return (ArrayList<DiscountItem>) this.discountItems.clone();
    }
}
