package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void testProductHasDisplayName() {
        //Arrange
        Product unit = new Product("soup");
        //Act
        String displayName = unit.displayName();
        //Assert
        assertEquals("soup", displayName);
    }
}
