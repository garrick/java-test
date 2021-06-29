package org.commandline.java.test.console;

import org.commandline.java.test.HenrysGrocery;

import java.util.HashMap;

public class BasketActionResolver {
    private final ConsoleWrapper console;
    private final HenrysGrocery henrysGrocery;
    private final BasketAction defaultBasketAction;
    private HashMap<String, BasketAction> actionRegistry = new HashMap<>();

    public BasketActionResolver(ConsoleWrapper console, HenrysGrocery henrysGrocery) {
        this.console = console;
        this.henrysGrocery = henrysGrocery;
        actionRegistry.put("x", new BasketActionAbandonCart(console));
        actionRegistry.put("b", new BasketActionShowBasket(console));
        actionRegistry.put("p", new BasketActionPay(console, henrysGrocery));
        this.defaultBasketAction = new BasketActionModifyBasket();
    }

    public BasketAction resolveFor(String inputValue) {
        return actionRegistry.getOrDefault(inputValue, defaultBasketAction);
    }
}
