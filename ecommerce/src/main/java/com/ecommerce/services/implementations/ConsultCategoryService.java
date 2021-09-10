package com.ecommerce.services.implementations;

import com.ecommerce.models.Category;
import com.ecommerce.repositories.implementations.CategoryRepositoryImplementation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ConsultCategoryService {
    private final CategoryRepositoryImplementation categoryRepositoryImplementation;

    public ConsultCategoryService(CategoryRepositoryImplementation categoryRepositoryImplementation) {
        this.categoryRepositoryImplementation = categoryRepositoryImplementation;
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepositoryImplementation.getCategoryById(categoryId);
    }
}
