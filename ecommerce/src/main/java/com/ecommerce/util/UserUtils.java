package com.ecommerce.util;

import com.ecommerce.models.CustomUser;
import com.ecommerce.models.Password;
import com.ecommerce.models.User;
import com.ecommerce.models.UserInformation;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    public User createCompleteUser(CustomUser customUser) {
        User user = ConversionUserPasswordUtility.CustomUserToUser(customUser);
        String rawPassword = customUser.getPassword();
        String encoded = new CustomPasswordEncoder().encode(rawPassword);
        Password password = ConversionUserPasswordUtility.CustomUserToPassword(customUser, user);
        password.setPassword(encoded);
        UserInformation userInformation = ConversionUserPasswordUtility.CustomUserToUserInformation(customUser, user);
        user.setPassword(password);
        user.setUserInformation(userInformation);
        user.setRole(customUser.getRole());
        return user;
    }
}
