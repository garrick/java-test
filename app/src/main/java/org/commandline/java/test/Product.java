package org.commandline.java.test;

public class Product {
    private final String name;

    public Product(String name) {
        if (name == null || "".equals(name)) throw new IllegalArgumentException("Null and blank Product names not allowed");
        this.name = name;
    }

    public String displayName() {
        return this.name;
    }
}
