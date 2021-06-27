/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.commandline.java.test;

import org.commandline.java.test.console.ConsoleWrapper;
import org.commandline.java.test.console.DefaultConsoleWrapper;

import java.time.LocalDateTime;

public class App {
    private final ConsoleWrapper consoleWrapper;
    private final HenrysGrocery henrysGrocery;
    private Basket basket;

    public App(ConsoleWrapper consoleWrapper, HenrysGrocery henrysGrocery) {
        this.consoleWrapper = consoleWrapper;
        this.henrysGrocery = henrysGrocery;
    }

    public String getGreeting() {
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to Henry's Grocery!\n");
        sb.append("===========================\n");
        sb.append("We sell all four items!\n");
        sb.append(getInventoryMessage());
        return sb.toString();
    }


    public static void main(String[] args) {
        HenrysGrocery henrysGrocery = new HenrysGrocery();
        App app = new App(new DefaultConsoleWrapper(), henrysGrocery);
        app.workflow();
    }

    public void workflow() {
        String dateString = consoleWrapper.readLine("Enter today's date in YYYY-MM-DD format:");
        String[] dateTimeBits = LocalDateTime.now().toString().split("T");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString + "T" + dateTimeBits[1]);
        System.out.println(localDateTime);
        consoleWrapper.printf(getGreeting());
        String lastItem = "";
        basket = new Basket(localDateTime);
        while (!"p".equals(lastItem) && !"x".equals(lastItem)) {
            lastItem = consoleWrapper.readLine("Select: [type number to add,'-number' to remove,'p' to pay, 'x' to exit]");
            processSelection(lastItem);
        }
    }

    private void processSelection(String value) {
        if ("x".equals(value)) {
            consoleWrapper.printf("Abandoned basket!");
        }

    }

    public String getInventoryMessage() {
        StringBuilder sb = new StringBuilder();
        String inventoryCSV = henrysGrocery.getInventoryAsCSV();
        String[] lines = inventoryCSV.split("\n");
        sb.append(String.format("\t Item# \t| %-7s \t| %-5s \t| %-5s \n", (Object[]) lines[0].split(",")));
        for (int i = 1; i < lines.length; i++) {
            sb.append(String.format(" \t" + i + " \t| %-7s \t| %-5s \t| %-5s \n", (Object[]) lines[i].split(",")));
        }
        return sb.toString();
    }

    public String convertSelectionToProduct(String productNumber) {
        try{
            int converted = Integer.parseInt(productNumber);
            if(converted == 1) return "soup";
        } catch (RuntimeException re){
        }
        return null;
    }

}
