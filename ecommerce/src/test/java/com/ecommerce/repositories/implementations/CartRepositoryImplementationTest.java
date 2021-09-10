package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.models.CartItem;
import com.ecommerce.repositories.specifications.CartRepository;
import com.ecommerce.utils.CartItemsBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
public class CartRepositoryImplementationTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    @DirtiesContext
    public void addProductToCardTest() {
        CartItem cartItem = cartRepository.addProductToCart(1L, 1L);
        CartItem copyCart = CartItemsBuilder.builder()
                .userId(1L)
                .productId(1L)
                .quantity(1L)
                .build();

        assertThat(cartItem).isEqualTo(copyCart);
    }

    @Test
    @DirtiesContext
    public void addProductIncreaseQuantity() {
        addCartItem(1L, 1L);
        CartItem cartItem = cartRepository.addProductToCart(1L, 1L);
        CartItem copyCart = CartItemsBuilder.builder()
                .userId(1L)
                .productId(1L)
                .quantity(2L)
                .build();

        assertThat(cartItem).isEqualTo(copyCart);
    }

    @Test
    @DirtiesContext
    public void testFindItem() {
        //GIVEN
        addCartItem(1L, 1L);
        addCartItem(1L, 2L);
        addCartItem(2L, 2L);
        //WHEN
        List<CartItem> cartItems = cartRepository.findCartItemsByUserId(1L);
        //THEN
        CartItem copyCart1 = CartItemsBuilder.builder()
                .userId(1L)
                .productId(1L)
                .quantity(1L)
                .build();

        CartItem copyCart2 = CartItemsBuilder.builder()
                .userId(1L)
                .productId(1L)
                .quantity(1L)
                .build();

        assertThat(cartItems).contains(copyCart1, copyCart2);
    }

    @Test
    @DirtiesContext
    public void testDeleteCartItem() {
        addCartItem(1L, 1L);
        cartRepository.deleteCartItem(1L, 1L);

        List<CartItem> cartItems = cartRepository.findCartItemsByUserId(1L);

        assertThat(cartItems).isEmpty();
    }

    @Test
    @DirtiesContext
    public void testDeleteCartItemsForUser() {
        addCartItem(1L, 1L);
        addCartItem(1L, 2L);
        addCartItem(2L, 2L);
        cartRepository.deleteCartItems(1L);

        List<CartItem> cartItems = cartRepository.findCartItemsByUserId(1L);

        assertThat(cartItems).isEmpty();
    }

    private void addCartItem(Long userId, Long productId) {
        cartRepository.addProductToCart(userId, productId);
    }

}

