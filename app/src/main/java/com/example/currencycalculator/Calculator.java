package com.example.currencycalculator;

public class Calculator {
    public double calculate(double value, Currency from, Currency to){
        return (value*from.getConversion_rate()*from.getAverage_rate()*to.getConversion_rate()/to.getAverage_rate());
    }
}
