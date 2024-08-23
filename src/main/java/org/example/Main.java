package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String EXIT_LOWER = "e";
    private static final String EXIT_UPPER = "E";
    private static List<ElectricityRate> priceEntries;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            if (isExitChoice(choice)) {
                break;
            }
            handleMenuChoice(choice, scanner);
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Elpriser");
        System.out.println("========");
        System.out.println("1. Inmatning");
        System.out.println("2. Min, Max och Medel");
        System.out.println("3. Sortera");
        System.out.println("4. Bästa Laddningstid (4h)");
        System.out.println("e. Avsluta");
    }

    private static boolean isExitChoice(String choice) {
        return choice.equalsIgnoreCase(EXIT_LOWER) || choice.equalsIgnoreCase(EXIT_UPPER);
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
                // Lägg till funktionalitet för Sortera här
                break;
            case "4":
                // Lägg till funktionalitet för Bästa Laddningstid här
                break;
            default:
                System.out.println("Ogiltigt val. Försök igen.");
                break;
        }
    }
}