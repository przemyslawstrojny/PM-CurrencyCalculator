package com.example.currencycalculator;

public class Currency {

    private String currency_name;
    private double conversion_rate;
    private String currency_code;
    private double average_rate;

    public Currency(String currency_name, double conversion_rate, String currency_code, double average_rate) {
        this.currency_name = currency_name;
        this.conversion_rate = conversion_rate;
        this.currency_code = currency_code;
        this.average_rate = average_rate;

    }

    public String getCurrency_name() {
        return currency_name;
    }

    public double getConversion_rate() {
        return conversion_rate;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public double getAverage_rate() {
        return average_rate;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public void setConversion_rate(double conversion_rate) {
        this.conversion_rate = conversion_rate;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public void setAverage_rate(double average_rate) {
        this.average_rate = average_rate;
    }
}
