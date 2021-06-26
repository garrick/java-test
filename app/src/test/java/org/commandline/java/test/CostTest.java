package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CostTest {
    @Test
    public void storeCurrencyInOneHundredthsAndFormatsCorrectlyForAPenny() {
        //Arrange
        Cost unit = new Cost(1);
        //Act
        String actualDisplayFormat = unit.displayFormat();
        //Assert
        assertEquals("0.01", actualDisplayFormat);
    }
}
