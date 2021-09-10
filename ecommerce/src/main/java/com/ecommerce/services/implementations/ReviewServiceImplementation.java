package com.ecommerce.services.implementations;

import com.ecommerce.models.Review;
import com.ecommerce.repositories.specifications.ReviewRepository;
import com.ecommerce.services.specifications.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImplementation implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImplementation(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review findById(Long id) {
        return this.reviewRepository.findById(id);
    }

    @Override
    public void save(Review review, Long idProduct, Long idUser) {
        reviewRepository.save(review, idProduct, idUser);
    }

    @Override
    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.reviewRepository.deleteById(id);
    }

    @Override
    public List<Review> getUserReviews(Long id) {
        return reviewRepository.getUserReviews(id);
    }

    @Override
    public List<Review> getProductReviews(Long id) {
        return reviewRepository.getProductReviews(id);
    }

}
