/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.commandline.java.test;

import org.commandline.java.test.console.ConsoleWrapper;
import org.commandline.java.test.console.DefaultConsoleWrapper;

public class App {
    private final ConsoleWrapper consoleWrapper;
    private final HenrysGrocery henrysGrocery;

    public App(ConsoleWrapper consoleWrapper, HenrysGrocery henrysGrocery) {
        this.consoleWrapper = consoleWrapper;
        this.henrysGrocery = henrysGrocery;
    }

    public String getGreeting() {
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to Henry's Grocery!\n");
        sb.append("===========================\n");
        sb.append("We sell all four items!\n");
        sb.append("Item# | Product | Unit | Cost |\n");

        return sb.toString();
    }

    public static void main(String[] args) {
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        App app = new App(new DefaultConsoleWrapper(), henrysGrocery);
        app.workflow();
    }

    public void workflow() {
        consoleWrapper.printf(getGreeting());
        String value = consoleWrapper.readLine("Select:");
    }

}
