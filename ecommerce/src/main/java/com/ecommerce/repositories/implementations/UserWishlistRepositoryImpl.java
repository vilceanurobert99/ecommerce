package com.ecommerce.repositories.implementations;

import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.CompositePK;
import com.ecommerce.models.Product;
import com.ecommerce.models.UserWishlist;
import com.ecommerce.repositories.specifications.UserWishlistRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class UserWishlistRepositoryImpl implements UserWishlistRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserWishlistRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Product> findWishlistItemsByUserId(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserWishlist> query = session.createQuery("FROM UserWishlist Where userId =: userId");
        query.setParameter("userId", userId);
        List<Long> productIds = query.list().stream().map(UserWishlist::getProductId).collect(toList());
        Query<Product> query1 = session.createQuery("FROM Product WHERE id =: productId");
        List<Product> productList = new ArrayList<>();
        productIds.forEach(id -> {
            query1.setParameter("productId", id);
            Product product = query1.getSingleResult();
            productList.add(product);
        });
        return productList;
    }

    @Override
    public Product findProductById(Long userId, Long productId) {
        Session session = sessionFactory.getCurrentSession();
        UserWishlist userWishlist = session.get(UserWishlist.class, new CompositePK(userId, productId));
        if(userWishlist == null) {
            throw new NotFoundException();
        }
        Product product = session.get(Product.class, userWishlist.getProductId());
        return product;
    }

    @Override
    public void addProductToWishlist(Long userId, Long productId) {
        Session session = sessionFactory.getCurrentSession();
        UserWishlist userWishlist = new UserWishlist();
        userWishlist.setUserId(userId);
        userWishlist.setProductId(productId);
        session.saveOrUpdate(userWishlist);
    }

    @Override
    public void deleteWishlistItems(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserWishlist> query = session.createQuery("FROM UserWishlist WHERE userId =: userId");
        query.setParameter("userId", userId);
        List<UserWishlist> userWishlists = query.list();
        userWishlists.forEach(session::delete);
    }

    @Override
    public void deleteWishlistItem(Long userId, Long productId) {
        Session session = sessionFactory.getCurrentSession();
        UserWishlist userWishlist = session.get(UserWishlist.class, new CompositePK(userId, productId));
        if(userWishlist == null) {
            throw new NotFoundException();
        }
        session.delete(userWishlist);
    }
}
