package com.ecommerce.services.implementations;

import com.ecommerce.repositories.implementations.CategoryRepositoryImplementation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AddCategoryService {

    private final CategoryRepositoryImplementation categoryRepositoryImplementation;

    public AddCategoryService(CategoryRepositoryImplementation categoryRepositoryImplementation) {
        this.categoryRepositoryImplementation = categoryRepositoryImplementation;
    }

    public void addCategory(String categoryName) {
        categoryRepositoryImplementation.addCategory(categoryName);
    }
}
