/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.commandline.java.test;

import org.commandline.java.test.console.FakeConsole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {
   private FakeConsole fakeConsole;
   private HenrysGrocery henrysGrocery;
   private App unit;

    @BeforeEach
    public void setUp() {
        //Arrange
        fakeConsole = new FakeConsole();
        henrysGrocery = new HenrysGrocery();
        unit = new App(fakeConsole, henrysGrocery);
    }

    @Test
    public void testWorkflowShowsMenuAndPromptsForDate(){
        //It's working backwards, to set up but a stack
        //seemed like a very easy way to fake user sequential input. --GW
        fakeConsole.pushInputStack("x");  //Leave w/o buying anything
        fakeConsole.pushInputStack("2021-06-26"); //Enter the date
        //Act
        unit.workflow();
        //Assert
        assertTrue(fakeConsole.getAllOutputAsString().contains("Welcome to Henry's Grocery!\n"));
    }

    @Test
    public void testBuildAndRenderInventory() {
        //Act
        String inventoryMessage = unit.getInventoryMessage();
        //Assert
        assertTrue(inventoryMessage.contains("Product"));
        assertTrue(inventoryMessage.contains("0.10"));
    }



    @Test
    public void testProcessSelectionAddsToBasketForValidPositiveItemIds(){
        //Arrange
        Basket basket = new Basket(LocalDateTime.now());
        int initialItemCount = basket.itemCount();
        //Act
        basket = unit.processSelection("1", basket);
        //Assert
        assertEquals(initialItemCount + 1, basket.itemCount());
    }

    @Test
    public void testProcessSelecitonRemovesItemFromBasketForValidNegativeIds() {
        //Arrange
        Basket basket = new Basket(LocalDateTime.now());
        basket = unit.processSelection("1", basket);
        //Act
        basket = unit.processSelection("-1", basket);
        //Assert
        assertEquals(0, basket.itemCount());
    }

    @Test
    public void testProcessSelectionShowsBasketWithB() {
        //Arrange
        Basket basket = new Basket(LocalDateTime.now());
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        //Act
        basket = unit.processSelection("b", basket);
        //Assert
        assertTrue(fakeConsole.getAllOutputAsString().contains("Your basket contains"));

    }

    @Test
    public void testProcessSelectionToQuitAppliesDiscountsAndShowsBasketConentsAndTotal(){
        //Arrange
        Basket basket = new Basket(LocalDateTime.now()){

        };
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("soup"));
        basket = basket.add(henrysGrocery.getStockItemByName("bread"));
        //Act
        basket = unit.processSelection("p", basket);
        //Assert
        assertTrue(fakeConsole.getAllOutputAsString().contains("Your basket contains: "));
        assertTrue(fakeConsole.getAllOutputAsString().contains("Your total:"));
    }
}
