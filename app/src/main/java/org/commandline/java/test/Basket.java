package org.commandline.java.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Basket {

    private final ArrayList<StockItem> items;
    private final LocalDateTime shoppingTime;

    public Basket(LocalDateTime shoppingTime) {
        this(new ArrayList<StockItem>(), shoppingTime);
    }

    private Basket(ArrayList<StockItem> items, LocalDateTime shoppingTime) {
        this.items = items;
        this.shoppingTime = shoppingTime;
    }

    public Basket add(StockItem stockItem) {
        ArrayList<StockItem> ourItems = (ArrayList<StockItem>) this.items.clone();
        ourItems.add(stockItem);
        return new Basket(ourItems, shoppingTime);
    }

    public int itemCount() {
        return items.size();
    }

    public ArrayList<StockItem> contents() {
        return (ArrayList<StockItem>) this.items.clone();
    }

    public long countProductByName(String productName) {
        return items.stream().filter(p -> productName.equals(p.productName())).count();
    }

    public LocalDateTime shoppingTime() {
        return this.shoppingTime;
    }

    public Basket remove(StockItem stockItemByName) {
        ArrayList<StockItem> ourItems = (ArrayList<StockItem>) this.items.clone();
        Optional<StockItem> first = ourItems.stream()
                .filter(i -> i.productName().equals(stockItemByName.productName()))
                .findFirst();
        StockItem found = first.get();
        if(found != null) ourItems.remove(found);
        return new Basket(ourItems, shoppingTime);
    }

    public String describeForShopper() {
        if(items.isEmpty()) return "Your shopping basket is empty.";
        HashMap<String, Integer> basketHistorgram = new HashMap<>();
        for(StockItem item : items) {
            Integer valueOrDefault = basketHistorgram.getOrDefault(item.productName(), 0);
            basketHistorgram.put(item.productName(), valueOrDefault + 1);
        }
        return "\nYour basket contains: "+basketHistorgram.toString()+"\n";
    }
}
