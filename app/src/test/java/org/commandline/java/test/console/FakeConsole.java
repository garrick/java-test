package org.commandline.java.test.console;

import java.util.ArrayList;

public class FakeConsole implements ConsoleWrapper {
    ArrayList<String> inputStrings = new ArrayList<>();
    ArrayList<String> outputStrings = new ArrayList<>();

    @Override
    public String readLine(String input) {
        inputStrings.add(input);
        return input;
    }

    @Override
    public void printf(String output) {
        outputStrings.add(output);
    }

    public ArrayList<String> getInputStrings() {
        return inputStrings;
    }

    public String getAllOutputAsString() {
        StringBuilder sb = new StringBuilder();
        outputStrings.forEach(s -> sb.append(s));
        return sb.toString();
    }
}
