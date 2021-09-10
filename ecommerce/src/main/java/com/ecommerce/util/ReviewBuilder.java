package com.ecommerce.util;

import com.ecommerce.models.Review;

import java.time.LocalDate;

public class ReviewBuilder {

        private int review;
        private String comment;
        private LocalDate reviewDate;

        public static ReviewBuilder builder() {
            return new ReviewBuilder();
        }

        public ReviewBuilder review(int review) {
            this.review = review;
            return this;
        }

        public ReviewBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public ReviewBuilder reviewDate(LocalDate reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public Review build() {
            Review review = new Review();
            review.setReview(this.review);
            review.setComment(this.comment);
            review.setReviewDate(this.reviewDate);

            return review;
        }
}
