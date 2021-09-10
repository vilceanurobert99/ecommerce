package com.ecommerce.controllers;

import com.ecommerce.models.Category;
import com.ecommerce.models.CategoryTypes;
import com.ecommerce.services.implementations.AddCategoryService;
import com.ecommerce.services.implementations.ConsultCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final ConsultCategoryService consultCategoryService;
    private final AddCategoryService addCategoryService;

    public CategoryController(ConsultCategoryService consultCategoryService, AddCategoryService addCategoryService) {
        this.consultCategoryService = consultCategoryService;
        this.addCategoryService = addCategoryService;

        // Insert categories just for test
        for(CategoryTypes categoryType: CategoryTypes.values()) {
            addCategoryService.addCategory(categoryType.name());
        }
    }

    @PostMapping("/{categoryName}")
    public ResponseEntity<HttpStatus> addCategory(@PathVariable("categoryName") String categoryName, Authentication auth) {
        if(UserController.hasAuthority(auth, "ADMIN")) {
            addCategoryService.addCategory(categoryName);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId) {
        return new ResponseEntity<Category>(consultCategoryService.getCategoryById(categoryId), HttpStatus.OK);
    }
}
