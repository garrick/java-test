package org.commandline.java.test.console;

import org.commandline.java.test.Basket;
import org.commandline.java.test.HenrysGrocery;

import static org.commandline.java.test.console.ProductFromItem.PRODUCT_UNKNOWN;

public class BasketActionModifyBasket extends BasketAction {
    private final HenrysGrocery henrysGrocery;

    public BasketActionModifyBasket(HenrysGrocery henrysGrocery){
        this.henrysGrocery = henrysGrocery;
    }

    @Override
    public Basket processBasket(String inputValue, Basket basket) {
        String product = henrysGrocery.productFromItemConvert(inputValue);
        if(!PRODUCT_UNKNOWN.equals(product)) {
            return (inputValue.startsWith("-") ? basket.remove(henrysGrocery.getStockItemByName(product)) :
                    basket.add(henrysGrocery.getStockItemByName(product)));
        }
        return basket;
    }
}
