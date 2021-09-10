package com.ecommerce.controllers;

import com.ecommerce.models.CartItem;
import com.ecommerce.models.CartProduct;
import com.ecommerce.services.implementations.AddProductToCartService;
import com.ecommerce.services.implementations.ConsultCartService;
import com.ecommerce.services.implementations.DeleteCartItemsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ConsultCartService consultCartService;
    private final AddProductToCartService addProductToCartService;
    private final DeleteCartItemsService deleteCartItemsService;

    public CartController(ConsultCartService consultCartService, AddProductToCartService addProductToCartService, DeleteCartItemsService deleteCartItemsService) {
        this.consultCartService = consultCartService;
        this.addProductToCartService = addProductToCartService;
        this.deleteCartItemsService = deleteCartItemsService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartProduct>> getUserCartItems(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(consultCartService.consult(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<CartItem> addProductToCart(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId
    ) {
        return new ResponseEntity<>(addProductToCartService.addProductToCart(userId, productId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUserCart(@PathVariable("userId") Long userId) {
        deleteCartItemsService.deleteCartItems(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<HttpStatus> deleteUserCartItem(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId) {
        deleteCartItemsService.deleteCartItem(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
