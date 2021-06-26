package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void testProductDisplayNameMustNotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Product unit = new Product("");
        });
    }

    @Test
    public void testProductDisplayNameMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Product unit = new Product(null);
        });
    }

}
