package com.ecommerce.repositories.specifications;

import com.ecommerce.models.Product;

import java.util.List;

public interface UserWishlistRepository {
    List<Product> findWishlistItemsByUserId(Long userId);
    Product findProductById(Long userId, Long productId);
    void addProductToWishlist(Long userId, Long productId);
    void deleteWishlistItems(Long userId);
    void deleteWishlistItem(Long userId, Long productId);
}
