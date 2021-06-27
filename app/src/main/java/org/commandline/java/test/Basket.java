package org.commandline.java.test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Basket {

    private ArrayList<StockItem> items = new ArrayList<>();
    public void add(StockItem stockItem) {
        this.items.add(stockItem);
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
}
