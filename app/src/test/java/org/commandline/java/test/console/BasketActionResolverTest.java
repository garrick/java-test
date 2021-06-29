package org.commandline.java.test.console;

import org.commandline.java.test.HenrysGrocery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketActionResolverTest {

    private ConsoleWrapper console;
    private HenrysGrocery henrysGrocery;
    private BasketActionResolver unit;
    private ProductFromItem productFromItem;

    @BeforeEach
    public void setUp() {
        //Arrange
        console = new FakeConsole();
        henrysGrocery = new HenrysGrocery();
        unit = new BasketActionResolver(console, henrysGrocery);
    }

    @Test
    public void testResolveForXReturnsSameObject() {
        //Act
        BasketAction firstAction = unit.resolveFor("x");
        BasketAction secondAction = unit.resolveFor("x");
        //Assert
        assertTrue(firstAction instanceof BasketActionAbandonCart);
        assertSame(firstAction, secondAction);
    }

    @Test
    public void testResolverForBReturnsSameObject() {
        //Act
        BasketAction firstAction = unit.resolveFor("b");
        BasketAction secondAction = unit.resolveFor("b");
        //Assert
        assertTrue(firstAction instanceof BasketActionShowBasket);
        assertSame(firstAction, secondAction);
    }

    @Test
    public void testResolverForPReturnsSameObject() {
        //Act
        BasketAction firstAction = unit.resolveFor("p");
        BasketAction secondAction = unit.resolveFor("p");
        //Assert
        assertTrue(firstAction instanceof BasketActionPay);
        assertSame(firstAction, secondAction);
    }

    @Test
    public void testResolverDefaultsToAttemptItemProcessingReturnsSameObject() {
        //Act
        BasketAction firstAction = unit.resolveFor("1");
        BasketAction secondAction = unit.resolveFor("1");
        //Assert
        assertTrue(firstAction instanceof BasketActionModifyBasket);
        assertSame(firstAction, secondAction);
    }
}
