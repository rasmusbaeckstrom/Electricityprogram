package org.example;

public class ElectricityRate {

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
}