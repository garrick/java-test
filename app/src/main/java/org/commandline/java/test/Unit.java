package org.commandline.java.test;

public class Unit {
    private final String unitName;

    public Unit(String unitName) {
        this.unitName = unitName;
    }

    public String displayName() {
        return this.unitName;
    }
}
