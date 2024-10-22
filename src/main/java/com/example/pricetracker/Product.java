package com.example.pricetracker;

public class Product {
    private String url;
    private String title;
    private double currentPrice;
    private double targetPrice;

    public Product(String url, String title, double currentPrice, double targetPrice) {
        this.url = url;
        this.title = title;
        this.currentPrice = currentPrice;
        this.targetPrice = targetPrice;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getTargetPrice() {
        return targetPrice;
    }
}
