package org.commandline.java.test.console;

import org.commandline.java.test.HenrysGrocery;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketActionResolverTest {

    @Test
    public void testResolveForXReturnsSameObject() {
        //Arrange
        FakeConsole console = new FakeConsole();
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        BasketActionResolver unit = new BasketActionResolver(console, henrysGrocery);
        //Act
        BasketAction firstAction = unit.resolveFor("x");
        BasketAction secondAction = unit.resolveFor("x");
        //Assert
        assertTrue(firstAction instanceof BasketActionAbandonCart);
        assertSame(firstAction, secondAction);
    }

    @Test
    public void testResolverForBReturnsSameObject() {
        //Arrange
        FakeConsole console = new FakeConsole();
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        BasketActionResolver unit = new BasketActionResolver(console, henrysGrocery);
        //Act
        BasketAction firstAction = unit.resolveFor("b");
        BasketAction secondAction = unit.resolveFor("b");
        //Assert
        assertTrue(firstAction instanceof BasketActionShowBasket);
        assertSame(firstAction, secondAction);
    }

    @Test
    public void testResolverForPReturnsSameObject() {
        //Arrange
        FakeConsole console = new FakeConsole();
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        BasketActionResolver unit = new BasketActionResolver(console, henrysGrocery);
        //Act
        BasketAction firstAction = unit.resolveFor("p");
        BasketAction secondAction = unit.resolveFor("p");
        //Assert
        assertTrue(firstAction instanceof BasketActionPay);
        assertSame(firstAction, secondAction);
    }
}
