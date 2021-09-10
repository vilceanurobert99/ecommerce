package com.ecommerce.controllers;

import com.ecommerce.models.UserAddress;
import com.ecommerce.services.specifications.UserAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/address")
public class UserAddressController {
    private final UserAddressService userAddressService;

    public UserAddressController(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UserAddress> saveUserAddress(@RequestBody UserAddress userAddress,
                                                       @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(userAddressService.save(userAddress, userId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddress> findByAddressId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userAddressService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAddress>> findAllAddresses(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(userAddressService.findUserAddresses(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteAddressesForUser(@PathVariable("userId") Long userId) {
        userAddressService.deleteAddressesForUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{addressId}")
    public ResponseEntity<HttpStatus> deleteAddressFromUser(@PathVariable("userId") Long userId,
                                                            @PathVariable("addressId") Long addressId) {
        userAddressService.deleteById(userId, addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
