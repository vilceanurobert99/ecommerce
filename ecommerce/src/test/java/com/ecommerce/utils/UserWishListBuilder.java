package com.ecommerce.utils;

import com.ecommerce.models.UserWishlist;

class UserWishListBuilder {
    private Long userId;
    private Long productId;

    public static UserWishListBuilder builder(){
        return new UserWishListBuilder();
    }

    public UserWishListBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserWishListBuilder productId(Long productId) {
        this.productId =  productId;
        return this;
    }

    public UserWishlist build() {
        UserWishlist userWishlist = new UserWishlist();
        userWishlist.setUserId(userId);
        userWishlist.setProductId(productId);
        return userWishlist;
    }

}