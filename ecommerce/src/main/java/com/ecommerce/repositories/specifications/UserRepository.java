package com.ecommerce.repositories.specifications;

import com.ecommerce.models.Password;
import com.ecommerce.models.User;
import com.ecommerce.models.UserInformation;

import java.util.List;

public interface UserRepository{

    User findByUsername(String username);
    default List<User> findAll() {
        return null;
    }
    User findById(Long id);
    default int count(){
        return 0;
    }
    User save(User user);
    void updateUserPassword(Password password);
    void updateUserInformation(UserInformation userInformation);
    void deleteById(Long id);
    void delete(User user);
}
