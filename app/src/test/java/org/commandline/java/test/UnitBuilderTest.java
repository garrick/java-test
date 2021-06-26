package org.commandline.java.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class UnitBuilderTest {

    @ParameterizedTest
    @ValueSource(strings = {"tin"})
    public void testWeKnowAboutTinUnitTypeNames(String typeName) {
        //Arrange
        UnitBuilder unit = new UnitBuilder();
        //Act
        Unit actualUnit = unit.buildUnitByName(typeName);
        //Assert
        assertEquals(typeName, actualUnit.displayName());
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
