package com.ecommerce.services.specifications;

import com.ecommerce.models.Product;
import com.ecommerce.models.ProductFilter;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product findById(Long id);
    void delete(Product product);
    List<Product> findAll();
    void deleteById(Long id);
    List<Product> filterProducts(ProductFilter productFilter);
    List<Product> filterProductsByName(String productName);
}
