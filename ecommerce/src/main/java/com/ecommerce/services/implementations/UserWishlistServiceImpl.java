package com.ecommerce.services.implementations;

import com.ecommerce.models.Product;
import com.ecommerce.repositories.specifications.UserWishlistRepository;
import com.ecommerce.services.specifications.UserWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserWishlistServiceImpl implements UserWishlistService {

    private final UserWishlistRepository userWishlistRepository;

    @Autowired
    public UserWishlistServiceImpl(UserWishlistRepository userWishlistRepository) {
        this.userWishlistRepository = userWishlistRepository;
    }

    @Override
    public List<Product> findWishlistItemsByUserId(Long userId) {
        return userWishlistRepository.findWishlistItemsByUserId(userId);
    }

    @Override
    public Product findProductById(Long userId, Long productId) {
        return userWishlistRepository.findProductById(userId, productId);
    }

    @Override
    public void addProductToWishlist(Long userId, Long productId) {
        userWishlistRepository.addProductToWishlist(userId, productId);
    }

    @Override
    public void deleteWishlistItems(Long userId) {
        userWishlistRepository.deleteWishlistItems(userId);
    }

    @Override
    public void deleteWishlistItem(Long userId, Long productId) {
        userWishlistRepository.deleteWishlistItem(userId, productId);
    }
}
