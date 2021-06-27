package org.commandline.java.test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Basket {

    private ArrayList<StockItem> items;
    private ArrayList<DiscountItem> discountItems;

    public Basket() {
        this(new ArrayList(), new ArrayList<>());
    }

    private Basket(ArrayList<StockItem> items, ArrayList<DiscountItem> discountItems) {
        this.items = items;
        this.discountItems = discountItems;
    }

    public Basket add(StockItem stockItem) {
        ArrayList<StockItem> ourItems = (ArrayList<StockItem>) this.items.clone();
        ourItems.add(stockItem);
        return new Basket(ourItems, discountItems);
    }

    public int itemCount() {
        return items.size();
    }

    public Cost totalCost() {
        var ref = new Object() {
            BigDecimal sum = new BigDecimal("0.00");
        };
        items.forEach((c) -> ref.sum = c.costAsBigDecimal().add(ref.sum));
        return new Cost(ref.sum.toString());
    }

    public Basket applyDiscount(TwoSoupGetsHalfPriceBreadDiscount discount) {
        DiscountItem discountItem = discount.check(this);
        if(discountItem != DiscountItem.NONE) {
            ArrayList<DiscountItem> ourDiscountItems = (ArrayList<DiscountItem>) this.discountItems.clone();
            ourDiscountItems.add(discountItem);
            return new Basket(items, ourDiscountItems);
        }
        return new Basket(items, discountItems);
    }

    public int discountsCount() {
        return discountItems.size();
    }

    public long countProductByName(String productName) {
        return items.stream().filter(p -> productName.equals(p.productName())).count();
    }
}
