package org.example;

public class ElectricityRate {
    private static final String TIME_PRICE_FORMAT = "Tid: %02d:00-%02d:00, Pris: %d öre/kWh";

    private int hour;
    private int rate;

    ElectricityRate(int hour, int rate) {
        this.hour = hour;
        this.rate = rate;
    }

    int getHour() {
        return hour;
    }

    int getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return String.format(TIME_PRICE_FORMAT, hour, hour + 1, rate);
    }
}