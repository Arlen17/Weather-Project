package com.example.weatherproject;

public enum Currency {
    KGZ("som",1),
    USD("dollar", 84.79),
    GBP("pound", 113.71),
    EUR("euro", 96.17),
    YEN("yen",0.74);

    private double somConversionRate;

    private String fullName;

    Currency(String fullName, double rupeeConversionRate) {
        this.somConversionRate = rupeeConversionRate;
        this.fullName = fullName;
    }

    public double getRupeeConversionRate() {
        return somConversionRate;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return name();
    }

}
