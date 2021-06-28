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
        BasketAction firstActionForX = unit.resolveFor("x");
        BasketAction secondActionForX = unit.resolveFor("x");
        //Assert
        assertTrue(firstActionForX instanceof BasketActionAbandonCart);
        assertSame(firstActionForX, secondActionForX );
    }
}
