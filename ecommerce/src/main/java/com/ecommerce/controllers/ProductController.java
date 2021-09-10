package com.ecommerce.controllers;

import com.ecommerce.models.Product;
import com.ecommerce.models.ProductFilter;
import com.ecommerce.services.specifications.ProductService;
import com.ecommerce.services.specifications.ReviewService;
import com.ecommerce.services.specifications.UserService;
import com.ecommerce.util.ProductMocker;
import com.ecommerce.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final UserUtils userUtils;

    @Autowired
    public ProductController(ProductService productService, ReviewService reviewService, UserService userService, UserUtils userUtils) {
        this.productService = productService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.userUtils = userUtils;

        new ProductMocker(productService, reviewService, this.userService, this.userUtils).mockProducts();
    }

    //ok
    @PostMapping("/insert")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product, Authentication auth) {
        if (UserController.hasAuthority(auth, "ADMIN")) {
            return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    //ok
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(@RequestBody ProductFilter filter) {
        return new ResponseEntity<>(productService.filterProducts(filter), HttpStatus.OK);
    }

    @GetMapping("/filter/{productName}")
    public ResponseEntity<List<Product>> filterProductsByName(@PathVariable("productName") String productName){
        return new ResponseEntity<>(productService.filterProductsByName(productName), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteProduct(@RequestBody Product product, Authentication auth) {
        if(UserController.hasAuthority(auth, "ADMIN")) {
            productService.delete(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable Long id, Authentication auth) {
        if(UserController.hasAuthority(auth, "ADMIN")) {
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
