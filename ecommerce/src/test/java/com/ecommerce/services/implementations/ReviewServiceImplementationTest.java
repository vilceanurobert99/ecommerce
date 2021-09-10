package com.ecommerce.services.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.models.Review;
import com.ecommerce.models.User;
import com.ecommerce.services.specifications.ProductService;
import com.ecommerce.services.specifications.ReviewService;
import com.ecommerce.services.specifications.UserService;
import com.ecommerce.util.ProductBuilder;
import com.ecommerce.util.ReviewBuilder;
import com.ecommerce.utils.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static com.ecommerce.models.CategoryTypes.ELECTROCASNICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
class ReviewServiceImplementationTest {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @BeforeEach
    public void init() {

        User user = UserBuilder.builder()
                .username("TEAM")
                .role("ADMIN")
                .password("project")
                .build();

        userService.save(user);

        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone 10")
                .price(4300)
                .sale(10)
                .category(category)
                .description("Ceva ceva")
                .quantity(3)
                .url("da")
                .build();
        productService.save(product);

        Review userReview = ReviewBuilder.builder()
                .review(5)
                .comment("Romania")
                .reviewDate(LocalDate.now())
                .build();

        reviewService.save(userReview, 1L, 1L);
    }

    @Test
    @DirtiesContext
    void findById() {
        Review userReview = ReviewBuilder.builder()
                .review(5)
                .comment("Romania")
                .reviewDate(LocalDate.now())
                .build();
        assertThat(reviewService.findById(4L)).isEqualTo(userReview);
    }

    @Test
    @DirtiesContext
    void findAll() {
        assertThat(reviewService.findAll()).size().isEqualTo(4);
        Review userReview = ReviewBuilder.builder()
                .review(3)
                .comment("Romania e foarte mare si mandra")
                .reviewDate(LocalDate.now())
                .build();
        reviewService.save(userReview, 1L, 1L);
        assertThat(reviewService.findAll()).size().isEqualTo(5);
    }

    @Test
    @DirtiesContext
    void deleteById() {
        reviewService.deleteById(4L);
        assertThat(reviewService.findAll()).size().isEqualTo(3);

        Review userReview = ReviewBuilder.builder()
                .review(3)
                .comment("Romania e foarte mare si mandra")
                .reviewDate(LocalDate.now())
                .build();
        reviewService.save(userReview, 1L, 1L);
        assertThat(reviewService.findAll()).size().isEqualTo(4);
        reviewService.deleteById(5L);
        assertThat(reviewService.findAll()).size().isEqualTo(3);
    }

    @Test
    @DirtiesContext
    void save() {
        Exception e = assertThrows(NotFoundException.class, () -> reviewService.findById(5L));
        assertThat(e).isInstanceOf(NotFoundException.class);
        Review userReview = ReviewBuilder.builder()
                .review(3)
                .comment("Romania e foarte mare si mandra")
                .reviewDate(LocalDate.now())
                .build();
        reviewService.save(userReview, 1L, 2L);
        assertThat(reviewService.findById(5L)).isEqualTo(userReview);
    }
}
