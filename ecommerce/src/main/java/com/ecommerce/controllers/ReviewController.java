package com.ecommerce.controllers;

import com.ecommerce.models.Review;
import com.ecommerce.services.specifications.ProductService;
import com.ecommerce.services.specifications.ReviewService;
import com.ecommerce.services.specifications.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, ProductService productService, UserService userService){
        this.reviewService = reviewService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/users/{idUser}")
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable("idUser") Long id){

        return new ResponseEntity<>(reviewService.getUserReviews(id), HttpStatus.OK);
    }

    @GetMapping("/products/{idProduct}")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable("idProduct") Long id){

        return new ResponseEntity<>(reviewService.getProductReviews(id), HttpStatus.OK);
    }

    @PostMapping("/{idProduct}/{idUser}")
    public ResponseEntity<HttpStatus> saveReview(@RequestBody Review review,
                                                 @PathVariable("idProduct") Long idProduct,
                                                 @PathVariable("idUser") Long idUser){

        reviewService.save(review, idProduct, idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
