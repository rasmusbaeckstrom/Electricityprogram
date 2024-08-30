package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String EXIT_COMMAND = "e";
    private static List<ElectricityRate> priceEntries;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                displayMenu();
                String choice = scanner.nextLine().trim();
                if (shouldExit(choice)) {
                    break;
                }
                handleMenuChoice(choice, scanner);
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Elpriser");
        System.out.println("========");
        System.out.println("1. Inmatning");
        System.out.println("2. Min, Max och Medel");
        System.out.println("3. Sortera");
        System.out.println("4. Bästa Laddningstid (4h)");
        System.out.println("5. Inläsning från fil");
        System.out.println("e. Avsluta");
    }

    private static boolean shouldExit(String choice) {
        return choice.equalsIgnoreCase(EXIT_COMMAND);
    }

    private static void handleMenuChoice(String choice, Scanner scanner) {
        switch (choice) {
            case "1":
                priceEntries = PriceInputHandler.inputPrices(scanner);
                break;
            case "2":
                PriceStatistics.displayMinMaxAverage(priceEntries);
                break;
            case "3":
                PriceStatistics.displayLowestToHighest(priceEntries);
                break;
            case "4":
                PriceStatistics.displayBestLoadingTime(priceEntries);
                break;
            case "5":
                priceEntries = PriceInputHandler.readPricesFromFile("elpriser.csv");
                break;
            default:
                System.out.println("Ogiltigt val. Försök igen.");
                break;
        }
    }
}