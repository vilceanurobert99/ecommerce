package com.ecommerce.util;

import com.ecommerce.models.Category;
import com.ecommerce.models.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Filter {
    private List<Product> products;

    private Filter(List<Product> products) {
        this.products = products;
    }

    public Filter filterCategory(Category category) {
        Optional<Category> optCategory = Optional.ofNullable(category);
        optCategory.ifPresent(cat -> this.products = products.stream()
                .filter(product -> product.getCategory().equals(cat))
                .collect(Collectors.toList()));
        return this;
    }

    public Filter filterRating(double rating) {
        Optional<Double> optRating = rating != 0.0 ? Optional.of(rating) : Optional.empty();
        optRating.ifPresent(rat -> this.products = products.stream()
                .filter(product -> product.getStars() >= rat)
                .collect(Collectors.toList()));
        return this;
    }

    public Filter filterPrice(double price) {
        Optional<Double> optPrice = price != 0.0 ? Optional.of(price) : Optional.empty();
        optPrice.ifPresent(pr -> this.products = products.stream()
                .filter(product -> product.getPrice() <= pr)
                .collect(Collectors.toList()));
        return this;
    }

    public Filter sortPrice(boolean arg) {
        if (arg) {
            products.sort(Product::compareTo);
        } else {
            products.sort(Comparator.reverseOrder());
        }
        return this;
    }

    public List<Product> collect() {
        return products;
    }

    public static Filter filterBuilder(List<Product> products) {
        return new Filter(products);
    }
}
