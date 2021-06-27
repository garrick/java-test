package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StockItemTest {

    @Test
    public void testAcceptableStockItem() {
        //Arrange
        UnitBuilder defaultUnitBuilder = new UnitBuilder();
        StockItem unit = new StockItem(new Product("Slurm"),
                defaultUnitBuilder.buildUnitByName("bottle"),
                new Cost(new BigDecimal("1.99")));
        //Act //Assert
        assertNotNull(unit);
    }

    @Test
    public void testUnacceptableStockItem() {
        //Arrange
        //Act //Assert
        UnitBuilder defaultUnitBuilder = new UnitBuilder();
        assertThrows(IllegalArgumentException.class, () -> {
            StockItem unit = new StockItem(new Product("Slurm"),
                    defaultUnitBuilder.buildUnitByName("hogshead"),
                    new Cost(new BigDecimal("21.99")));
        });
    }
}