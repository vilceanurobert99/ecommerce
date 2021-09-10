package com.ecommerce.utils;

import com.ecommerce.models.HistoryItems;

import java.time.LocalDate;

public class HistoryItemsBuilder {
    private Long userId;
    private Long productId;
    private final Long quantity = 1L;
    private LocalDate purchase;

    public static HistoryItemsBuilder builder() {
        return new HistoryItemsBuilder();
    }

    public HistoryItemsBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public HistoryItemsBuilder productId(Long productId) {
        this.productId = productId;
        return this;
    }

    public HistoryItemsBuilder purchase() {
        purchase = LocalDate.now();
        return this;
    }

    public HistoryItems build() {
        HistoryItems historyItem = new HistoryItems();
        historyItem.setUserId(userId);
        historyItem.setProductId(productId);
        historyItem.setQuantity(quantity);
        historyItem.setPurchaseDate(purchase);
        return historyItem;
    }
}
