package com.ecommerce.services.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.User;
import com.ecommerce.models.UserAddress;
import com.ecommerce.repositories.specifications.UserRepository;
import com.ecommerce.services.specifications.UserAddressService;
import com.ecommerce.utils.UserAddressBuilder;
import com.ecommerce.utils.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
class UserAddressServiceImplementationTest {

    @Autowired
    private UserAddressService userAddressService;

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
        userAddressService.save(userAddress, 2L);
    }

    @Test
    @DirtiesContext
    public void testSaveAddress() {
        UserAddress userAddress = getAddress();
        UserAddress saveAddress = userAddressService.save(userAddress, 2L);
        assertThat(userAddress).isEqualTo(saveAddress);
    }

    @Test
    @DirtiesContext
    public void testFindAddressById() {
        UserAddress userAddress = getAddress();
        userAddressService.save(userAddress, 2L);
        UserAddress foundAddress = userAddressService.findById(2L);
        assertThat(userAddress).isEqualTo(foundAddress);
    }

    @Test
    @DirtiesContext
    public void testFindUserException(){
        Exception e = assertThrows(NotFoundException.class, () -> userAddressService.findById(10L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testFindUserAddresses(){
        userAddressService.save(getAddress(), 2L);
        assertThat(userAddressService.findUserAddresses(2L)).size().isEqualTo(2);
    }

    @Test
    @DirtiesContext
    public void testFindUserAddressesException() {
        Exception e = assertThrows(NotFoundException.class, () -> userAddressService.findUserAddresses(10L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testDeleteAddress() {
        userAddressService.deleteById(2L, 1L);
        Exception e = assertThrows(NotFoundException.class, () -> userAddressService.findById(1L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testDeleteAddresses() {
        userAddressService.save(getAddress(), 2L);
        userAddressService.deleteAddressesForUser(2L);
        assertThat(userAddressService.findUserAddresses(2L)).size().isEqualTo(0);
    }

    private UserAddress getAddress() {
        return UserAddressBuilder.builder()
                .city("Timisoara")
                .country("Romania")
                .address("Strada Ripensiei 13")
                .build();
    }
}