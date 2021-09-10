package com.ecommerce.services.implementations;

import com.ecommerce.models.UserAddress;
import com.ecommerce.repositories.specifications.UserAddressRepository;
import com.ecommerce.services.specifications.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserAddressServiceImplementation implements UserAddressService {

    private UserAddressRepository userAddressRepository;

    @Autowired
    public UserAddressServiceImplementation(UserAddressRepository userAddressRepository) {
        this.userAddressRepository = userAddressRepository;
    }

    @Override
    public List<UserAddress> findUserAddresses(Long idUser) {
        return userAddressRepository.findUserAddresses(idUser);
    }

    @Override
    public UserAddress findById(Long addressId) {
        return userAddressRepository.findById(addressId);
    }

    @Override
    public UserAddress save(UserAddress userAddress, Long id) {
        return userAddressRepository.save(userAddress, id);
    }


    @Override
    public void deleteById(Long userId, Long id) {
        userAddressRepository.deleteById(userId,id);
    }

    @Override
    public void deleteAddressesForUser(Long idUser) {
        userAddressRepository.deleteAddressesForUser(idUser);
    }
}
