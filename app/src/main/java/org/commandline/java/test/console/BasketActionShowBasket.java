package org.commandline.java.test.console;

import org.commandline.java.test.Basket;

public class BasketActionShowBasket extends BasketAction {
    private ConsoleWrapper consoleWrapper;

    public BasketActionShowBasket(ConsoleWrapper consoleWrapper) {
        this.consoleWrapper = consoleWrapper;
    }
    @Override
    public Basket processBasket(String inputValue, Basket basket) {
        consoleWrapper.printf(basket.describeForShopper());
        return basket;
    }
}
