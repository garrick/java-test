package org.commandline.java.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Basket {

    private ArrayList<StockItem> items;
    private ArrayList<DiscountItem> discountItems;
    private LocalDateTime shoppingTime;

    public Basket(LocalDateTime shoppingTime) {
        this(new ArrayList(), new ArrayList<>(), shoppingTime);
    }

    private Basket(ArrayList<StockItem> items, ArrayList<DiscountItem> discountItems, LocalDateTime shoppingTime) {
        this.items = items;
        this.discountItems = discountItems;
        this.shoppingTime = shoppingTime;
    }

    public Basket add(StockItem stockItem) {
        ArrayList<StockItem> ourItems = (ArrayList<StockItem>) this.items.clone();
        ourItems.add(stockItem);
        return new Basket(ourItems, discountItems, shoppingTime);
    }

    public int itemCount() {
        return items.size();
    }

    public Cost totalCost() {
        var ref = new Object() {
            BigDecimal sum = new BigDecimal("0.00");
        };
        items.forEach((c) -> ref.sum = c.costAsBigDecimal().add(ref.sum));
        var discountRef = new Object() {
            BigDecimal discountSum = new BigDecimal("0.00");
        };
        discountItems.forEach((d) -> discountRef.discountSum = d.discountAmount().add(discountRef.discountSum));
        return new Cost(ref.sum.subtract(discountRef.discountSum).toString());
    }

    public Basket applyDiscount(TwoSoupGetsHalfPriceBreadDiscount discount) {
        DiscountItem discountItem = discount.check(this);
        if(discountItem != DiscountItem.NONE) {
            ArrayList<DiscountItem> ourDiscountItems = (ArrayList<DiscountItem>) this.discountItems.clone();
            ourDiscountItems.add(discountItem);
            return new Basket(items, ourDiscountItems, shoppingTime);
        }
        return new Basket(items, discountItems, shoppingTime);
    }

    public int discountsCount() {
        return discountItems.size();
    }

    public long countProductByName(String productName) {
        return items.stream().filter(p -> productName.equals(p.productName())).count();
    }

    public LocalDateTime shoppingTime() {
        return this.shoppingTime;
    }
}
