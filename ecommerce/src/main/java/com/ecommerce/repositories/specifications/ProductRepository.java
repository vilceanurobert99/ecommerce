package com.ecommerce.repositories.specifications;

import com.ecommerce.models.Product;
import com.ecommerce.models.ProductFilter;

import java.util.List;

public interface ProductRepository {

    List<Product> filter(ProductFilter productFilter);
    Product save(Product entity);
    Product findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
    void delete(Product entity);
    List<Product> filterProductsByName(String productName);
}
