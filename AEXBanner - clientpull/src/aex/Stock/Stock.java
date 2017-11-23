package aex.Stock;

import java.io.Serializable;

public class Stock implements IStock, Serializable {

    private String name;
    private double rate;

    public Stock(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getRate() {
        return rate;
    }
}
