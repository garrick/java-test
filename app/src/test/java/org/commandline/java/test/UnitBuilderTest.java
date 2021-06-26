package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class UnitBuilderTest {
    @Test
    public void testWeKnowAboutTinUnitTypes() {
        //Arrange
        UnitBuilder unit = new UnitBuilder();
        //Act
        Unit actualUnit = unit.buildUnitByName("tin");
        //Assert
        assertEquals("tin", actualUnit.displayName());
    }

    @Test
    public void testUnknownUnitTypeNameReturnsUnitUnknownConstant() {
        //Arrange
        UnitBuilder unit = new UnitBuilder();
        //Act
        Unit actualUnit = unit.buildUnitByName("hogshead");
        //Assert
        assertSame(Unit.UNKNOWN, actualUnit);
    }
}
