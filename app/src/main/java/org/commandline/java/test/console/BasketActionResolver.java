package org.commandline.java.test.console;

import org.commandline.java.test.HenrysGrocery;

import java.util.HashMap;

public class BasketActionResolver {
    private final ConsoleWrapper console;
    private final HenrysGrocery henrysGrocery;
    private HashMap<String, BasketAction> actionRegistry = new HashMap<>();

    public BasketActionResolver(ConsoleWrapper console, HenrysGrocery henrysGrocery) {
        this.console = console;
        this.henrysGrocery = henrysGrocery;
        actionRegistry.put("x", new BasketActionAbandonCart());
        actionRegistry.put("b", new BasketActionShowBasket());
    }

    public BasketAction resolveFor(String inputValue) {
        return actionRegistry.get(inputValue);
    }
}
