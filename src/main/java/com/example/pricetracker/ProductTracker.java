package com.example.pricetracker;

import java.util.ArrayList;
import java.util.List;

public class ProductTracker {
    private final List<Product> productList = new ArrayList<>();

    public void addProduct(String url, String title, double currentPrice, double targetPrice) {
        Product product = new Product(url, title, currentPrice, targetPrice);
        productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }
}
