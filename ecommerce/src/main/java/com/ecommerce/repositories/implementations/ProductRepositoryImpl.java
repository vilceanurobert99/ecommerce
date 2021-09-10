package com.ecommerce.repositories.implementations;

import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.models.ProductFilter;
import com.ecommerce.models.Review;
import com.ecommerce.repositories.specifications.ProductRepository;
import com.ecommerce.util.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final SessionFactory sessionFactory;

    public ProductRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product save(Product product) {

        Session session = sessionFactory.getCurrentSession();
        Query<Category> query = session.createQuery("FROM Category WHERE name =: name");
        query.setParameter("name", product.getCategory().getName());
        Category category;

        try{
            category = query.getSingleResult();
        }catch (NoResultException r){
            throw new NotFoundException();
        }

        product.setCategory(category);
        session.saveOrUpdate(product);

        session.flush();

        return product;
    }

    private List<Review> getReviewsForProduct(Long productId, Session session) {
        return null;
    }

    @Override
    public Product findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        if (product == null){
            throw new NotFoundException();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("FROM Product");
        List<Product> products = query.list();
        return products;
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product currentProduct = session.get(Product.class, id);
        session.delete(currentProduct);
    }

    @Override
    public void delete(Product product) {
        Session session = sessionFactory.getCurrentSession();
        Product dbProduct = session.get(Product.class, product.getId());
        session.delete(dbProduct);
    }

    @Override
    public List<Product> filterProductsByName(String productName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("FROM Product WHERE UPPER(name) LIKE ?1");
        query.setParameter(1, "%"+productName.toUpperCase()+"%");
        List<Product> products = query.getResultList();
        return products;
    }

    @Override
    public List<Product> filter(ProductFilter productFilter){
        List<Product> products = this.findAll();
        return Filter.filterBuilder(products)
                .filterCategory(productFilter.getCategory())
                .filterRating(productFilter.getRating())
                .filterPrice(productFilter.getPrice())
                .sortPrice(productFilter.getIsAscending())
                .collect();
    }
}
