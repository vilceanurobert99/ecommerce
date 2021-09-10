package com.ecommerce.services.implementations;

import com.ecommerce.repositories.implementations.CartRepositoryImplementation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeleteCartItemsService {

    private final CartRepositoryImplementation cartRepositoryImplementation;

    public DeleteCartItemsService(CartRepositoryImplementation cartRepositoryImplementation) {
        this.cartRepositoryImplementation = cartRepositoryImplementation;
    }

    public void deleteCartItems(Long userId) {
        cartRepositoryImplementation.deleteCartItems(userId);
    }

    public void deleteCartItem(Long userId, Long productId) {
        cartRepositoryImplementation.deleteCartItem(userId, productId);
    }
}
