package com.ecommerce.services.implementations;

import com.ecommerce.models.CartItem;
import com.ecommerce.repositories.implementations.CartRepositoryImplementation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AddProductToCartService {

    private final CartRepositoryImplementation cartRepositoryImplementation;

    public AddProductToCartService(CartRepositoryImplementation cartRepositoryImplementation) {
        this.cartRepositoryImplementation = cartRepositoryImplementation;
    }

    public CartItem addProductToCart(Long userId, Long productId) {
        return cartRepositoryImplementation.addProductToCart(userId, productId);
    }
}
