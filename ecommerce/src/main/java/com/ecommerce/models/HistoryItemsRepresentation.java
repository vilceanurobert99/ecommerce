package com.ecommerce.models;

import com.ecommerce.services.specifications.ProductService;

import java.time.LocalDate;

public class HistoryItemsRepresentation {
    public Product product;
    public Long quantity;
    public LocalDate purchaseDate;

    private ProductService productService;

    public HistoryItemsRepresentation(ProductService productService) {
        this.productService = productService;
    }

    public HistoryItemsRepresentation mapFromDomain(HistoryItems historyItems) {
        Product product = productService.findById(historyItems.getProductId());
        this.product = product;
        this.quantity = historyItems.getQuantity();
        this.purchaseDate = historyItems.getPurchaseDate();

        return this;
    }
}
