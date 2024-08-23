package org.example;

import java.util.List;

public class PriceStatistics {

    public static void displayMinMaxAverage(List<ElectricityRate> prices) {
        if (prices == null || prices.isEmpty()) {
            System.out.println("Ingen data tillgänglig.");
            return;
        }

        ElectricityRate minRate = prices.get(0);
        ElectricityRate maxRate = prices.get(0);
        double sum = 0;

        for (ElectricityRate rate : prices) {
            if (rate.getRate() < minRate.getRate()) {
                minRate = rate;
            }
            if (rate.getRate() > maxRate.getRate()) {
                maxRate = rate;
            }
            sum += rate.getRate();
        }

        double average = sum / prices.size();

        System.out.println("Lägsta pris: " + minRate.getRate() + " öre mellan " + formatHour(minRate.getHour()));
        System.out.println("Högsta pris: " + maxRate.getRate() + " öre mellan " + formatHour(maxRate.getHour()));
        System.out.println("Medelpris: " + String.format("%.2f", average) + " öre");
    }

    private static String formatHour(int hour) {
        int endHour = (hour + 1) % 24;
        return String.format(PriceInputHandler.TIME_FORMAT_PATTERN, hour, endHour);
    }
}