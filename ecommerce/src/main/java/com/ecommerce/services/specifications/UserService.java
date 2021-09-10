package com.ecommerce.services.specifications;

import com.ecommerce.models.Password;
import com.ecommerce.models.User;
import com.ecommerce.models.UserInformation;

public interface UserService {

    User findById(Long id);

    User save(User user);

    User findByUsername(String username);

    void deleteById(Long id);

    void delete(User user);

    void updatePassword(Password password);

    void updateUserInformation(UserInformation userInformation);
}
