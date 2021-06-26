package org.commandline.java.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnitBuilderTest {
    @Test
    public void testWeKnowAboutTinUnitTypes() {
        //Arrange
        UnitBuilder unit = new UnitBuilder();
        //Act
        Unit actualUnit = unit.buildUnitByName("tin");
        //Assert
        assertNotNull(actualUnit);
    }
}
