package com.ecommerce.services.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.repositories.specifications.ProductRepository;
import com.ecommerce.services.specifications.UserWishlistService;
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
class UserWishlistServiceImplTest {

    @Autowired
    private UserWishlistService userWishlistService;
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
                .url("jpg")
                .category(category)
                .build();
        productRepository.save(product);
        userWishlistService.addProductToWishlist(1L, 1L);
    }

    @Test
    @DirtiesContext
    void TestFindWishlistItemsByUserId() {
        assertThat(userWishlistService.findWishlistItemsByUserId(1L)).size().isEqualTo(1);
        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product2 = ProductBuilder.builder()
                .name("Iphone 11")
                .price(5000)
                .sale(0)
                .description("bla")
                .url("jpg")
                .category(category)
                .build();
        productRepository.save(product2);
        userWishlistService.addProductToWishlist(1L, 2L);
        assertThat(userWishlistService.findWishlistItemsByUserId(1L)).size().isEqualTo(2);

    }

    @Test
    @DirtiesContext
    public void testFindUserWishListById() {
        userWishlistService.addProductToWishlist(1L, 13L);
        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone X")
                .price(5000)
                .sale(0)
                .description("bla")
                .url("jpg")
                .category(category)
                .build();
        assertThat(userWishlistService.findProductById(1L, 13L)).isEqualTo(product);
    }

    @Test
    @DirtiesContext
    public void testFindUserWishlist() {
        userWishlistService.addProductToWishlist(2L,1L);
        userWishlistService.addProductToWishlist(1L,2L);
        assertThat(userWishlistService.findWishlistItemsByUserId(1L)).size().isEqualTo(2);
    }

    @Test
    @DirtiesContext
    public void testDeleteWishlistItem(){
        userWishlistService.deleteWishlistItem(1L, 1L);
        Exception e = assertThrows(NotFoundException.class, () -> userWishlistService.findProductById(1L, 1L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testDeleteWishlistItems(){
        userWishlistService.addProductToWishlist(2L,1L);
        userWishlistService.addProductToWishlist(1L,2L);
        userWishlistService.deleteWishlistItems(1L);
        assertThat(userWishlistService.findWishlistItemsByUserId(1L)).size().isEqualTo(0);
        userWishlistService.addProductToWishlist(1L,2L);
        assertThat(userWishlistService.findWishlistItemsByUserId(1L)).size().isEqualTo(1);
        assertThat(userWishlistService.findWishlistItemsByUserId(2L)).size().isEqualTo(1);
        userWishlistService.deleteWishlistItems(2L);
        assertThat(userWishlistService.findWishlistItemsByUserId(2L)).size().isEqualTo(0);
    }

}

