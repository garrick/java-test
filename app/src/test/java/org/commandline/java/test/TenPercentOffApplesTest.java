package org.commandline.java.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TenPercentOffApplesTest {

    private HenrysGrocery henrysGrocery;
    private TenPercentOffApples unit;

    @BeforeEach
    public void setUp() {
        //Arrange
        henrysGrocery = new HenrysGrocery();
        unit = new TenPercentOffApples(henrysGrocery);
    }

    @Test
    public void testIsCurrentlyAvailableIsTrue() {
        //Act
        //Assert
        assertTrue(unit.isCurrentlyAvailable(LocalDateTime.now().plusDays(3)));
    }

    @Test
    public void testIsCurrentlyAvailableIsFalseTooEarly() {
        //Act
        //Assert
        assertFalse(unit.isCurrentlyAvailable(LocalDateTime.now().plusDays(2)));
    }

    @Test
    public void testIsCurrentlyAvailableIsFalseTooLate() {
        //Arrange
        LocalDateTime startOfThisMonth = LocalDateTime.now().minusDays(LocalDateTime.now().getDayOfMonth());
        LocalDateTime endOfFollowingMonthPlusOneDay = startOfThisMonth.plusMonths(2).plusDays(1);
        //Act
        //Assert
        assertFalse(unit.isCurrentlyAvailable(endOfFollowingMonthPlusOneDay));
    }

    @Test
    public void testCheckForDiscountIsOutOfDateReturnsUnavailable() {
        //Arrange
        Basket basket = new Basket(LocalDateTime.now());
        //Act
        //Assert
        assertSame(DiscountItem.NONE, unit.check(basket));
    }
}
