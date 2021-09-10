package com.ecommerce.repositories.specifications;

import com.ecommerce.models.CartItem;

import java.util.List;

public interface CartRepository {
    List<CartItem> findCartItemsByUserId(Long userId);
    CartItem addProductToCart(Long userId, Long productId);
    void deleteCartItems(Long userId);
    void deleteCartItem(Long userId, Long productId);
}
