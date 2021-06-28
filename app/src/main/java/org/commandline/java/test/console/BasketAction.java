package org.commandline.java.test.console;

import org.commandline.java.test.Basket;

public abstract class BasketAction {
    public abstract Basket processBasket(String inputValue, Basket basket);
}
