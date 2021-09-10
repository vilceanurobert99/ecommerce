package com.ecommerce.services.specifications;

import com.ecommerce.models.Review;

import java.util.List;

public interface ReviewService {

    Review findById(Long id);

    void save(Review review, Long idProduct, Long idUser);

    List<Review> findAll();

    void deleteById(Long id);

    List<Review> getUserReviews(Long id);

    List<Review> getProductReviews(Long id);
}
