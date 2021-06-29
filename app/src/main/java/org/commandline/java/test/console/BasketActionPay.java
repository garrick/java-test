package org.commandline.java.test.console;

import org.commandline.java.test.Basket;
import org.commandline.java.test.HenrysGrocery;
import org.commandline.java.test.TenPercentOffApples;
import org.commandline.java.test.TwoSoupGetsHalfPriceBreadDiscount;

public class BasketActionPay extends BasketAction {
    private final ConsoleWrapper consoleWrapper;
    private final HenrysGrocery henrysGrocery;

    public BasketActionPay(ConsoleWrapper consoleWrapper, HenrysGrocery henrysGrocery){
        this.consoleWrapper = consoleWrapper;
        this.henrysGrocery = henrysGrocery;
    }

    @Override
    public Basket processBasket(String inputValue, Basket basket) {
        Basket resultBasket = basket.applyDiscount(new TenPercentOffApples(henrysGrocery));
        resultBasket = resultBasket.applyDiscount(new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery));
        consoleWrapper.printf(resultBasket.describeForShopper());
        consoleWrapper.printf("Your total: "+ resultBasket.totalCost());
        return resultBasket;
    }
}
