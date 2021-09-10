package com.ecommerce.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Value("0")
    @Column(name = "review")
    private int review;

    @Column(name = "comment")
    private String comment;

    @NotNull
    @Column(name = "review_date")
    private LocalDate reviewDate;

    @NotNull
    @Column(name = "user_review")
    private Long userId;

    @NotNull
    @Column(name = "product_review")
    private Long productId;

    public Review() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", review=" + review +
                ", comment='" + comment + '\'' +
                ", reviewDate=" + reviewDate +
                ", productId=" + productId +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review1 = (Review) o;
        return review == review1.review && Objects.equals(comment, review1.comment) && Objects.equals(reviewDate, review1.reviewDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(review, comment, reviewDate);
    }
}
