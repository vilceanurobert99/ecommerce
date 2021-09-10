package com.ecommerce.utils;

import com.ecommerce.models.Password;
import com.ecommerce.models.User;
import com.ecommerce.util.CustomPasswordEncoder;

public class UserBuilder{
    private String username;
    private Password password;
    private String role;
    private Long id;

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public UserBuilder username(String username){
        this.username = username;
        return this;
    }

    public UserBuilder role(String role) {
        this.role = role;
        return this;
    }

    public UserBuilder password(String password) {
        CustomPasswordEncoder encoder = new CustomPasswordEncoder();
        password = encoder.encode(password);
        Password pass = new Password();
        pass.setPassword(password);
        this.password = pass;
        return this;
    }

    public UserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public User build() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setId(id);
        return user;
    }
}
