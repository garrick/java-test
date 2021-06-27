package org.commandline.java.test.console;

import java.util.ArrayList;
import java.util.Stack;

public class FakeConsole implements ConsoleWrapper {
    Stack<String> inputStrings = new Stack<>();
    ArrayList<String> outputStrings = new ArrayList<>();

    @Override
    public String readLine(String input) {
        outputStrings.add(input);
        return inputStrings.pop();
    }

    @Override
    public void printf(String output) {
        outputStrings.add(output);
    }

    public String getAllOutputAsString() {
        StringBuilder sb = new StringBuilder();
        outputStrings.forEach(s -> sb.append(s));
        return sb.toString();
    }

    public void pushInputStack(String input) {
        inputStrings.push(input);
    }
}
