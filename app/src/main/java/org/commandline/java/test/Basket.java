package org.commandline.java.test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Basket {

    private ArrayList<StockItem> items;

    public Basket() {
        this(new ArrayList());
    }
    public Basket(ArrayList<StockItem> newItems) {
        this.items = newItems;
    }
    public Basket add(StockItem stockItem) {
        ArrayList<StockItem> ourItems = (ArrayList<StockItem>) this.items.clone();
        ourItems.add(stockItem);
        return new Basket(ourItems);
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
        return new Basket(items);
    }

    public int discountsCount() {
        return 0;
    }

    public long countProductByName(String productName) {
        return items.stream().filter(p -> productName.equals(p.productName())).count();
    }
}
