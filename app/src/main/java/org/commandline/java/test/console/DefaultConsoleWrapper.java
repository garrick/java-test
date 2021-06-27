package org.commandline.java.test.console;

import java.util.Scanner;

public class DefaultConsoleWrapper implements ConsoleWrapper {

    @Override
    public String readLine(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void printf(String output) {
        System.out.printf(output);
    }
}
