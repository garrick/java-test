package org.commandline.java.test;

public class UnitBuilder {
    public Unit buildUnitByName(String unitName) {
        return new Unit(unitName);
    }
}
