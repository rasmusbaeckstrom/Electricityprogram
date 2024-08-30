package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceInputHandler {
    static final String TIME_FORMAT_PATTERN = "%02d:00-%02d:00";
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
            System.out.print("Ange priset i öre för tidsperioden " + String.format(TIME_FORMAT_PATTERN, hour, hour + 1) + ": ");
            try {
                int price = Integer.parseInt(scanner.nextLine());
                if (price < 0) {
                    System.out.println("Priset kan inte vara negativt. Försök igen.");
                } else {
                    return price;
                }
            } catch (NumberFormatException e) {
                System.out.println("Felaktig inmatning. Ange priset i öre, t.ex 115.");
            }
        }
    }

    public static List<ElectricityRate> readPricesFromFile(String fileName) {
        List<ElectricityRate> priceEntries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String timeRange = values[0].trim();
                int rate = Integer.parseInt(values[1].trim());

                String[] timeParts = timeRange.split("[:-]");
                int hour = Integer.parseInt(timeParts[0].trim());

                priceEntries.add(new ElectricityRate(hour, rate));
            }
            System.out.println("Priser har lästs in från filen.");
        } catch (IOException e) {
            System.out.println("Fel vid läsning från fil: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Felaktigt format i filen: " + e.getMessage());
        }
        return priceEntries;
    }
}