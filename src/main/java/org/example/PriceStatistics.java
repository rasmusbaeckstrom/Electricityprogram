package org.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PriceStatistics {

    public static void displayMinMaxAverage(List<ElectricityRate> priceEntries) {
        if (priceEntries == null || priceEntries.isEmpty()) {
            System.out.println("Ingen data tillgänglig.");
            return;
        }

        ElectricityRate minRate = priceEntries.get(0);
        ElectricityRate maxRate = priceEntries.get(0);
        double sum = 0;

        for (ElectricityRate rate : priceEntries) {
            if (rate.getRate() < minRate.getRate()) {
                minRate = rate;
            }
            if (rate.getRate() > maxRate.getRate()) {
                maxRate = rate;
            }
            sum += rate.getRate();
        }

        double average = sum / priceEntries.size();

        System.out.println("Lägsta pris: " + minRate.getRate() + " öre mellan " + formatHour(minRate.getHour()));
        System.out.println("Högsta pris: " + maxRate.getRate() + " öre mellan " + formatHour(maxRate.getHour()));
        System.out.println("Medelpris: " + String.format("%.2f", average) + " öre");
    }

    private static String formatHour(int hour) {
        int endHour = (hour + 1) % 24;
        return String.format(PriceInputHandler.TIME_FORMAT_PATTERN, hour, endHour);
    }

    public static void displayLowestToHighest(List<ElectricityRate> priceEntries) {
        if (priceEntries == null || priceEntries.isEmpty()) {
            System.out.println("Ingen data tillgänglig.");
            return;
        }

        Collections.sort(priceEntries, Comparator.comparingDouble(ElectricityRate::getRate));

        for (ElectricityRate rate : priceEntries) {
            System.out.println(rate.getRate() + " öre mellan " + formatHour(rate.getHour()));
        }
    }
}