package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

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

    @Test
    public void testIsCurrentlyAvailableIsFalseTooLate() {
        //Arrange
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        TenPercentOffApples unit = new TenPercentOffApples(henrysGrocery);
        //Act
        LocalDateTime startOfThisMonth = LocalDateTime.now().minusDays(LocalDateTime.now().getDayOfMonth());
        LocalDateTime endOfFollowingMonthPlusOneDay = startOfThisMonth.plusMonths(2).plusDays(1);
        boolean isAvailable = unit.isCurrentlyAvailable(endOfFollowingMonthPlusOneDay);
        //Assert
        assertFalse(isAvailable);
    }
}
