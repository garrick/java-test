package org.commandline.java.test;

import java.util.ArrayList;
import java.util.List;

public class UnitBuilder {
    private static final ArrayList knownUnits = new ArrayList(List.of("tin", "loaf"));

    public Unit buildUnitByName(String unitName) {
        return knownUnits.contains(unitName) ? new Unit(unitName) : Unit.UNKNOWN;
    }
}
