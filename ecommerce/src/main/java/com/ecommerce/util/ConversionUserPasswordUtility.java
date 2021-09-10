package com.ecommerce.util;

import com.ecommerce.models.CustomUser;
import com.ecommerce.models.Password;
import com.ecommerce.models.User;
import com.ecommerce.models.UserInformation;

import java.util.ArrayList;

public class ConversionUserPasswordUtility {

    public static User CustomUserToUser(CustomUser customUser){

        User user = new User();
        user.setId(null);
        user.setUsername(customUser.getUsername());
        init(user);
        return user;
    }

    private static void init(User user){
        user.setUserAddresses(new ArrayList<>());
    }

    public static UserInformation CustomUserToUserInformation(CustomUser customUser, User user){

        UserInformation userInformation = new UserInformation();
        userInformation.setId(null);
        userInformation.setFirstName(customUser.getFirstName());
        userInformation.setLastName(customUser.getLastName());
        userInformation.setEmail(customUser.getEmail());
        userInformation.setPhone(customUser.getPhone());
        return userInformation;
    }

    public static Password CustomUserToPassword(CustomUser customUser, User user){

        Password password = new Password();
        password.setId(null);
        password.setPassword(customUser.getPassword());
        password.setOldPassword("");
        return password;
    }
}
