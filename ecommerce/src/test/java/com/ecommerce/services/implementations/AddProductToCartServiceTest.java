package com.ecommerce.services.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.models.CartItem;
import com.ecommerce.utils.CartItemsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
class AddProductToCartServiceTest {

    @Autowired
    private AddProductToCartService addProductToCartService;

    @Test
    @DirtiesContext
    public void addProductToCardTest() {
        CartItem cartItem = addProductToCartService.addProductToCart(1L, 1L);
        CartItem copyCart = CartItemsBuilder.builder()
                .userId(1L)
                .productId(1L)
                .quantity(1L)
                .build();

        assertThat(cartItem).isEqualTo(copyCart);
    }
}