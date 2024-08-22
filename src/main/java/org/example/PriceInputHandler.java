package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceInputHandler {
    private static final String TIME_FORMAT_PATTERN = "%02d:00-%02d:00";
    private static final String COMPLETION_MESSAGE = "Inmatningen är klar. Du har matat in priser för hela dygnet.";

    public static List<ElectricityRate> inputPrices(Scanner scanner) {
        List<ElectricityRate> priceEntries = new ArrayList<>();
        addPriceEntries(scanner, priceEntries);
        System.out.println(COMPLETION_MESSAGE);
        return priceEntries;
    }

    private static void addPriceEntries(Scanner scanner, List<ElectricityRate> priceEntries) {
        for (int hour = 0; hour < 24; hour++) {
            int price = promptForPrice(scanner, hour);
            priceEntries.add(new ElectricityRate(hour, price));
        }
    }

    private static int promptForPrice(Scanner scanner, int hour) {
        while (true) {
            System.out.print("Ange priset i öre för perioden " + String.format(TIME_FORMAT_PATTERN, hour, hour + 1) + ": ");
            try {
                int price = Integer.parseInt(scanner.nextLine());
                if (price < 0) {
                    System.out.println("Priset kan inte vara negativt. Försök igen.");
                } else {
                    return price;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ogiltig inmatning. Ange ett giltigt heltal.");
            }
        }
    }
}