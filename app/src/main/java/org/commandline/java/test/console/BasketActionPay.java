package org.commandline.java.test.console;

import org.commandline.java.test.*;

public class BasketActionPay extends BasketAction {
    private final ConsoleWrapper consoleWrapper;
    private final HenrysGrocery henrysGrocery;

    public BasketActionPay(ConsoleWrapper consoleWrapper, HenrysGrocery henrysGrocery){
        this.consoleWrapper = consoleWrapper;
        this.henrysGrocery = henrysGrocery;
    }

    @Override
    public Basket processBasket(String inputValue, Basket resultBasket) {
        PointOfSale pointOfSale = new PointOfSale();
        Discounts discounts = new Discounts();
        discounts = discounts.applyDiscount(resultBasket, new TenPercentOffApples(henrysGrocery));
        discounts = discounts.applyDiscount(resultBasket, new TwoSoupGetsHalfPriceBreadDiscount(henrysGrocery));
        consoleWrapper.printf(resultBasket.describeForShopper());
        consoleWrapper.printf("Your total: " + pointOfSale.totalCost(resultBasket.contents(), discounts));
        return resultBasket;
    }
}
