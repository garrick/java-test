package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TenPercentOffApplesTest {

    @Test
    public void testIsCurrentlyAvailableIsTrue() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        TenPercentOffApples unit = new TenPercentOffApples(henrysGrocery);
        //Act
        boolean isAvailable = unit.isCurrentlyAvailable(LocalDateTime.now().plusDays(3));
        //Assert
        assertTrue(isAvailable);
    }

    @Test
    public void testIsCurrentlyAvailableIsFalseTooEarly() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        TenPercentOffApples unit = new TenPercentOffApples(henrysGrocery);
        //Act
        boolean isAvailable = unit.isCurrentlyAvailable(LocalDateTime.now().plusDays(2));
        //Assert
        assertFalse(isAvailable);
    }
}
