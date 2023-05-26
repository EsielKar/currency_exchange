package com.esielkar.currency_exchange.model;

public class Currency {
    private String id;
    private double value;

    public Currency(String id, double value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getCurrencyValue() {
        return 1 / value;
    }
}
