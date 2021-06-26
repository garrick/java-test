package org.commandline.java.test;

public class Product {
    private final String name;

    public Product(String name) {

        this.name = name;
    }

    public String displayName() {
        return this.name;
    }
}
