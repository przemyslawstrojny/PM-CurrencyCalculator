package com.example.currencycalculator;

public class Calculator {
    public double calculate(double value, Currency from, Currency to){
        return (value*from.getConversion_rate()*from.getAverage_rate()*to.getConversion_rate()/to.getAverage_rate());
    }

    public double calculate2(double quantity,Currency from, Currency to) {
       return (from.getAverage_rate()/to.getAverage_rate()) *  quantity;
    }
}
