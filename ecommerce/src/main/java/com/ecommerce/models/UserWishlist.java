package com.ecommerce.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_wishlist")
@IdClass(CompositePK.class)
public class UserWishlist implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "product_id")
    private Long productId;

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
        return "CartItems{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
