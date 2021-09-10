package com.ecommerce.utils;

import com.ecommerce.models.CartItem;

public class CartItemsBuilder {
    private long userId;
    private long productId;
    private long quantity;

    public static CartItemsBuilder builder() {
        return new CartItemsBuilder();
    }

    public CartItemsBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public CartItemsBuilder productId(Long productId) {
        this.productId = productId;
        return this;
    }

    public CartItemsBuilder quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public CartItem build() {
        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);
        return cartItem;
    }
}
