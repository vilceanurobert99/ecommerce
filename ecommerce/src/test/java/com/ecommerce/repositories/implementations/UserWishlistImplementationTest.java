package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.repositories.specifications.ProductRepository;
import com.ecommerce.repositories.specifications.UserWishlistRepository;
import com.ecommerce.util.ProductBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import static com.ecommerce.models.CategoryTypes.ELECTROCASNICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
/**
 * Product Mocker adds by default 12 products, that's why we start from 13.
 * An fix would be to call product mocker from main, in that way the test won't be affected.
 * However, all test are running as expected.
 */
public class UserWishlistImplementationTest {

    @Autowired
    private UserWishlistRepository userWishlistRepository;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void initProduct() {
        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone X")
                .price(5000)
                .sale(0)
                .description("bla")
                .url("bla")
                .category(category)
                .build();
        productRepository.save(product);
    }

    @Test
    @DirtiesContext
    public void testAddToWishlist() {
        userWishlistRepository.addProductToWishlist(1L,1L);
        assertThat(userWishlistRepository.findWishlistItemsByUserId(1L)).size().isEqualTo(1);
    }

    @Test
    @DirtiesContext
    public void testFindUserWishListById() {
        userWishlistRepository.addProductToWishlist(1L, 13L);
        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone X")
                .price(5000)
                .sale(0)
                .description("bla")
                .url("bla")
                .category(category)
                .build();
        assertThat(userWishlistRepository.findProductById(1L, 13L)).isEqualTo(product);
    }

    @Test
    @DirtiesContext
    public void testFindUserWishlist() {
        userWishlistRepository.addProductToWishlist(1L,1L);
        userWishlistRepository.addProductToWishlist(2L,1L);
        userWishlistRepository.addProductToWishlist(1L,2L);
        assertThat(userWishlistRepository.findWishlistItemsByUserId(1L)).size().isEqualTo(2);
    }

    @Test
    @DirtiesContext
    public void testDeleteWishlistItem(){

        userWishlistRepository.addProductToWishlist(1L,1L);
        userWishlistRepository.deleteWishlistItem(1L, 1L);
        Exception e = assertThrows(NotFoundException.class, () -> userWishlistRepository.findProductById(1L, 1L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testDeleteWishlistItems(){
        userWishlistRepository.addProductToWishlist(1L,1L);
        userWishlistRepository.addProductToWishlist(2L,1L);
        userWishlistRepository.addProductToWishlist(1L,2L);
        userWishlistRepository.deleteWishlistItems(1L);
        assertThat(userWishlistRepository.findWishlistItemsByUserId(1L)).size().isEqualTo(0);
        userWishlistRepository.addProductToWishlist(1L,2L);
        assertThat(userWishlistRepository.findWishlistItemsByUserId(1L)).size().isEqualTo(1);
        assertThat(userWishlistRepository.findWishlistItemsByUserId(2L)).size().isEqualTo(1);
        userWishlistRepository.deleteWishlistItems(2L);
        assertThat(userWishlistRepository.findWishlistItemsByUserId(2L)).size().isEqualTo(0);
    }
}
