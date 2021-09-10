package com.ecommerce.services.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.models.Category;
import com.ecommerce.repositories.implementations.CategoryRepositoryImplementation;
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
class ConsultCategoryServiceTest {

    @Autowired
    private ConsultCategoryService consultCategoryService;

    @Autowired
    private AddCategoryService addCategory;

    @Test
    @DirtiesContext
    public void testAddAndFindCategory() {
        addCategory.addCategory("Telefoane");
        Category category = consultCategoryService.getCategoryById(5L);
        assertThat(category.getName()).isEqualTo("Telefoane");
    }
}