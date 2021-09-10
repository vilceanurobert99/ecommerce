package com.ecommerce.services.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.models.CartItem;
import com.ecommerce.models.Category;
import com.ecommerce.repositories.implementations.CategoryRepositoryImplementation;
import com.ecommerce.repositories.specifications.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
class DeleteCartItemsServiceTest {

    @Autowired
    private DeleteCartItemsService deleteCartItem;

    @Autowired
    private CartRepository cartRepository;

    @Test
    @DirtiesContext
    public void testDeleteCartItem() {
        addCartItem(1L, 1L);
        deleteCartItem.deleteCartItem(1L, 1L);

        List<CartItem> cartItems = cartRepository.findCartItemsByUserId(1L);

        assertThat(cartItems).isEmpty();
    }

    @Test
    @DirtiesContext
    public void testDeleteCartItemsForUser() {
        addCartItem(1L, 1L);
        addCartItem(1L, 2L);
        addCartItem(2L, 2L);
        deleteCartItem.deleteCartItems(1L);

        List<CartItem> cartItems = cartRepository.findCartItemsByUserId(1L);

        assertThat(cartItems).isEmpty();
    }

    private void addCartItem(Long userId, Long productId) {
        cartRepository.addProductToCart(userId, productId);
    }
}