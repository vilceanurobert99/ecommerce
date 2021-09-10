package com.ecommerce.models;

public class ProductFilter {

    private Category category;
    private double price;
    private boolean isAscending = false;
    private double rating;

    public ProductFilter() {
    }

    public boolean getIsAscending() {
        return isAscending;
    }

    public void setIsAscending(boolean isAscending) {
        this.isAscending = isAscending;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
