package com.ecommerce.utils;

import com.ecommerce.models.UserAddress;

public class UserAddressBuilder {
    private String country;
    private String city;
    private String address;

    public static UserAddressBuilder builder() {
        return new UserAddressBuilder();
    }

    public UserAddressBuilder country(String country) {
        this.country = country;
        return this;
    }

    public UserAddressBuilder city(String city) {
        this.city = city;
        return this;
    }

    public UserAddressBuilder address(String address) {
        this.address = address;
        return this;
    }

    public UserAddress build() {
        UserAddress userAddress = new UserAddress();
        userAddress.setAddress(address);
        userAddress.setCity(city);
        userAddress.setCountry(country);
        return userAddress;
    }
}

