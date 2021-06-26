package org.commandline.java.test;

public class Product {
    private final String name;

    public Product(String name) {
        if ("".equals(name)) throw new IllegalArgumentException();
        this.name = name;
    }

    public String displayName() {
        return this.name;
    }
}
