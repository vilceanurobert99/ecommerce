package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.User;
import com.ecommerce.models.UserAddress;
import com.ecommerce.repositories.specifications.UserAddressRepository;
import com.ecommerce.repositories.specifications.UserRepository;
import com.ecommerce.utils.UserAddressBuilder;
import com.ecommerce.utils.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
public class UserAddressRepositoryImplementationTest {
    @Autowired
    private UserAddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        User user = UserBuilder.builder()
                .username("TEAM")
                .role("ADMIN")
                .password("project")
                .build();

        userRepository.save(user);
        UserAddress userAddress = UserAddressBuilder.builder()
                .city("Timisoara")
                .country("Romania")
                .address("Strada Ripensiei 16")
                .build();
        addressRepository.save(userAddress, 2L);
    }

    @Test
    @DirtiesContext
    public void testSaveAddress() {
        UserAddress userAddress = getAddress();
        UserAddress saveAddress = addressRepository.save(userAddress, 2L);
        assertThat(userAddress).isEqualTo(saveAddress);
    }

    @Test
    @DirtiesContext
    public void testFindAddressById() {
        UserAddress userAddress = getAddress();
        addressRepository.save(userAddress, 2L);
        UserAddress foundAddress = addressRepository.findById(2L);
        assertThat(userAddress).isEqualTo(foundAddress);
    }

    @Test
    @DirtiesContext
    public void testFindUserException(){
       Exception e = assertThrows(NotFoundException.class, () -> addressRepository.findById(10L));
       assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testFindUserAddresses(){
        addressRepository.save(getAddress(), 2L);
        assertThat(addressRepository.findUserAddresses(2L)).size().isEqualTo(2);
    }

    @Test
    @DirtiesContext
    public void testFindUserAddressesException() {
        Exception e = assertThrows(NotFoundException.class, () -> addressRepository.findUserAddresses(10L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testDeleteAddress() {
        addressRepository.deleteById(2L, 1L);
        Exception e = assertThrows(NotFoundException.class, () -> addressRepository.findById(1L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testDeleteAddresses() {
        addressRepository.save(getAddress(), 2L);
        addressRepository.deleteAddressesForUser(2L);
        assertThat(addressRepository.findUserAddresses(2L)).size().isEqualTo(0);
    }

    private UserAddress getAddress() {
        return UserAddressBuilder.builder()
                .city("Timisoara")
                .country("Romania")
                .address("Strada Ripensiei 13")
                .build();
    }
}