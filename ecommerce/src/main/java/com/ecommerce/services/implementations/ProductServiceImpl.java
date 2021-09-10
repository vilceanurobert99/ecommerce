package com.ecommerce.services.implementations;

import com.ecommerce.models.Product;
import com.ecommerce.models.ProductFilter;
import com.ecommerce.repositories.specifications.ProductRepository;
import com.ecommerce.services.specifications.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> filterProducts(ProductFilter productFilter) {
        return productRepository.filter(productFilter);
    }

    @Override
    public List<Product> filterProductsByName(String productName) {
        return productRepository.filterProductsByName(productName);
    }
}
