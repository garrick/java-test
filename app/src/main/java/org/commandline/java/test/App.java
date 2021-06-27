/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.commandline.java.test;

import org.commandline.java.test.console.ConsoleWrapper;
import org.commandline.java.test.console.DefaultConsoleWrapper;

import java.time.LocalDateTime;
import java.util.HashMap;

public class App {
    private final ConsoleWrapper consoleWrapper;
    private final HenrysGrocery henrysGrocery;
    private Basket basket;
    private HashMap<String,String> itemIdToProductHashMap = new HashMap<>();
    private String inventoryMessage = "";

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
        app.buildInventoryData();
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

    public void buildInventoryData() {
        StringBuilder sb = new StringBuilder();
        String inventoryCSV = henrysGrocery.getInventoryAsCSV();
        String[] lines = inventoryCSV.split("\n");
        sb.append(String.format("\t Item# \t| %-7s \t| %-5s \t| %-5s \n", (Object[]) lines[0].split(",")));
        for (int i = 1; i < lines.length; i++) {
            Object[] columns = (Object[]) lines[i].split(",");
            sb.append(String.format(" \t" + i + " \t| %-7s \t| %-5s \t| %-5s \n", columns));
            itemIdToProductHashMap.put(""+i,""+columns[0]);
        }
        inventoryMessage = sb.toString();
    }

    public String getInventoryMessage() {
        return inventoryMessage;
    }

    public String convertSelectionToProduct(String productNumber) {
        try{
            int converted = Integer.parseInt(productNumber);
            return itemIdToProductHashMap.get(""+Math.abs(converted));
        } catch (RuntimeException re){
        }
        return null;
    }

}
