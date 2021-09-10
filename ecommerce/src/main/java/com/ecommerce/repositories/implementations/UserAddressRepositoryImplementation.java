package com.ecommerce.repositories.implementations;

import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.User;
import com.ecommerce.models.UserAddress;
import com.ecommerce.repositories.specifications.UserAddressRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class UserAddressRepositoryImplementation implements UserAddressRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserAddressRepositoryImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserAddress> findUserAddresses(Long idUser) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, idUser);
        if(user == null) {
            throw new NotFoundException();
        }
        Hibernate.initialize(user.getUserAddresses());
        return user.getUserAddresses();
    }

    @Override
    public UserAddress findById(Long addressId) {
        Session session = sessionFactory.getCurrentSession();
        UserAddress userAddress = session.get(UserAddress.class, addressId);
        if (userAddress == null) {
            throw new NotFoundException();
        }
        return userAddress;
    }

    @Override
    public UserAddress save(UserAddress userAddress, Long userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        Hibernate.initialize(user.getUserAddresses());
        userAddress.setId(null);
        user.addUserAddress(userAddress);
        session.saveOrUpdate(userAddress);
        return userAddress;
    }

    @Override
    public void deleteById(Long userId, Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        UserAddress userAddress = session.get(UserAddress.class, id);
        if (user == null || userAddress == null) {
            throw new NotFoundException();
        }
        Hibernate.initialize(user.getUserAddresses());
        if (!user.getUserAddresses().contains(userAddress)) {
            throw new NotFoundException();
        }
        user.removeAddress(userAddress);
        session.delete(userAddress);
    }

    @Override
    public void deleteAddressesForUser(Long idUser) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, idUser);
        if (user == null) {
            throw new NotFoundException();
        }
        Hibernate.initialize(user.getUserAddresses());
        user.getUserAddresses().forEach((session::delete));
        user.clearAddresses();
    }
}
