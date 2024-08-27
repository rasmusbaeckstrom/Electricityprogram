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
        System.out.println("Dygnets medelpris: " + String.format("%.2f", average) + " öre");
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

    public static void displayBestLoadingTime(List<ElectricityRate> priceEntries) {
        if (priceEntries == null || priceEntries.isEmpty() || priceEntries.size() < 4) {
            System.out.println("Ingen data tillgänglig.");
            return;
        }

        double minSum = Double.MAX_VALUE;
        int bestStartIndex = 0;

        for (int i = 0; i < priceEntries.size(); i++) {
            double currentSum = 0;
            for (int j = 0; j < 4; j++) {
                currentSum += priceEntries.get((i + j) % priceEntries.size()).getRate();
            }

            System.out.println("Kontrollerar tidsintervall: " + formatHour(priceEntries.get(i).getHour()) +
                    " till " + formatHour(priceEntries.get((i + 3) % priceEntries.size()).getHour()) +
                    " - Summa för 4 timmar: " + currentSum);

            if (currentSum < minSum) {
                minSum = currentSum;
                bestStartIndex = i;
            }
        }

        double averagePrice = minSum / 4;
        int startHour = priceEntries.get(bestStartIndex).getHour();

        System.out.println("Bästa starttid att ladda: " + formatHour(startHour) +
                " med ett medelpris på " + String.format("%.2f", averagePrice) +
                " öre under 4 timmar.");
    }
}



