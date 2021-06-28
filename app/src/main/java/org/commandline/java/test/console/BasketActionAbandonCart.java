package org.commandline.java.test.console;

import org.commandline.java.test.Basket;

public class BasketActionAbandonCart extends BasketAction {
    private ConsoleWrapper consoleWrapper;

    public BasketActionAbandonCart(ConsoleWrapper consoleWrapper) {
        this.consoleWrapper = consoleWrapper;
    }
    @Override
    public Basket processBasket(String inputValue, Basket basket) {
        consoleWrapper.printf("Abandoned basket!");
        return basket;

    }
}
