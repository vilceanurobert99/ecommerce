package com.ecommerce.models;

import java.io.Serializable;
import java.util.Objects;

public class CompositePK implements Serializable {
    private Long userId;
    private Long productId;

    public CompositePK(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public CompositePK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositePK)) return false;
        CompositePK that = (CompositePK) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }
}
