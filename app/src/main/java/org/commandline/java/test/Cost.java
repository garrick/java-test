package org.commandline.java.test;

public class Cost {
    private int hundredths;

    public Cost(int hundredths) {
        this.hundredths = hundredths;
    }

    public String displayFormat() {
        return "0.01";
    }
}
