/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.commandline.java.test;

import org.commandline.java.test.console.FakeConsole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        unit.buildInventoryData();
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
    public void convertSelectionIntOneToStringSoup() {
        //Act
        String productName = unit.convertSelectionToProduct("1");
        //Assert
        assertEquals("soup", productName);
    }

    @Test
    public void convertSelectionIntNegativeOneToStringSoup() {
        //Act
        String productName = unit.convertSelectionToProduct("-1");
        //Assert
        assertEquals("soup", productName);
    }

    @Test
    public void convertSelectionDefaultsLookupToProductUnknown(){
        //Act
        String productName = unit.convertSelectionToProduct("1138");
        //Assert
        assertEquals("PRODUCT_UNKNOWN", productName);
    }

    @Test
    public void convertSelectionHandlesInvalidIdsWithProductUnknown(){
        //Act
        String productName = unit.convertSelectionToProduct("THX-1138");
        //Assert
        assertEquals("PRODUCT_UNKNOWN", productName);
    }
}
