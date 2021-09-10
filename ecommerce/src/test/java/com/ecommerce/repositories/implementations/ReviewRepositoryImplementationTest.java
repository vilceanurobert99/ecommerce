package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.models.Review;
import com.ecommerce.models.User;
import com.ecommerce.repositories.specifications.ProductRepository;
import com.ecommerce.repositories.specifications.ReviewRepository;
import com.ecommerce.repositories.specifications.UserRepository;
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
/**
 * Product Mocker adds by default 3 reviews, that's why we start from 4.
 * An fix would be to call product mocker from main, in that way the test won't be affected.
 * However, all test are running as expected.
 */
@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
class ReviewRepositoryImplementationTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {

        User user = UserBuilder.builder()
                .username("TEAM")
                .role("ADMIN")
                .password("project")
                .build();

        userRepository.save(user);

        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Casti")
                .category(category)
                .price(450.00)
                .sale(6)
                .quantity(100)
                .description("joc.")
                .url("jpg")
                .build();
        productRepository.save(product);

        Review userReview = ReviewBuilder.builder()
                .review(5)
                .comment("Romania")
                .reviewDate(LocalDate.now())
                .build();

        reviewRepository.save(userReview, 1L, 1L);
    }

    @Test
    @DirtiesContext
    void findById() {
        Review userReview = ReviewBuilder.builder()
                .review(5)
                .comment("Romania")
                .reviewDate(LocalDate.now())
                .build();
        assertThat(reviewRepository.findById(4L)).isEqualTo(userReview);
    }

    @Test
    @DirtiesContext
    void findAll() {
        assertThat(reviewRepository.findAll()).size().isEqualTo(4);
        Review userReview = ReviewBuilder.builder()
                .review(3)
                .comment("Romania e foarte mare si mandra")
                .reviewDate(LocalDate.now())
                .build();
        reviewRepository.save(userReview, 1L, 1L);
        assertThat(reviewRepository.findAll()).size().isEqualTo(5);
    }

    @Test
    @DirtiesContext
    void deleteById() {
        reviewRepository.deleteById(4L);
        assertThat(reviewRepository.findAll()).size().isEqualTo(3);

        Review userReview = ReviewBuilder.builder()
                .review(3)
                .comment("Romania e foarte mare si mandra")
                .reviewDate(LocalDate.now())
                .build();
        reviewRepository.save(userReview, 1L, 1L);
        assertThat(reviewRepository.findAll()).size().isEqualTo(4);
        reviewRepository.deleteById(5L);
        assertThat(reviewRepository.findAll()).size().isEqualTo(3);
    }

    @Test
    @DirtiesContext
    void save() {
        Exception e = assertThrows(NotFoundException.class, () -> reviewRepository.findById(5L));
        assertThat(e).isInstanceOf(NotFoundException.class);
        Review userReview = ReviewBuilder.builder()
                .review(3)
                .comment("Romania e foarte mare si mandra")
                .reviewDate(LocalDate.now())
                .build();
        reviewRepository.save(userReview, 1L, 2L);
        assertThat(reviewRepository.findById(5L)).isEqualTo(userReview);
    }
}
