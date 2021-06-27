package org.commandline.java.test;

import java.util.ArrayList;

public class Basket {

    private ArrayList<StockItem> items = new ArrayList<>();
    public void add(StockItem stockItem) {
        this.items.add(stockItem);
    }

    public int itemCount() {
        return items.size();
    }
}
