package com.ecommerce.repositories.implementations;

import com.ecommerce.models.CartItem;
import com.ecommerce.models.CompositePK;
import com.ecommerce.repositories.specifications.CartRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepositoryImplementation implements CartRepository {

    private final SessionFactory sessionFactory;

    public CartRepositoryImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CartItem> findCartItemsByUserId(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<CartItem> query = session.createQuery("FROM CartItem WHERE userId =: id");
        query.setParameter("id", userId);
        return query.list();
    }

    @Override
    public CartItem addProductToCart(Long userId, Long productId) {
        Session session = sessionFactory.getCurrentSession();
        CartItem cartItem = new CartItem();

        cartItem.setUserId(userId);
        cartItem.setProductId(productId);
        cartItem.setQuantity(1L);

        CartItem maybeCartItem = session.get(CartItem.class, new CompositePK(userId, productId));
        if(maybeCartItem == null) {
            session.save(cartItem);
        } else {
            maybeCartItem.setQuantity(maybeCartItem.getQuantity() + 1);
            session.saveOrUpdate(maybeCartItem);

            return maybeCartItem;
        }

        return cartItem;
    }

    @Override
    public void deleteCartItems(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<CartItem> query = session.createQuery("DELETE FROM CartItem WHERE userId =: id");
        query.setParameter("id", userId);
        query.executeUpdate();
    }

    @Override
    public void deleteCartItem(Long userId, Long productId) {
        Session session = sessionFactory.getCurrentSession();
        CartItem cartItem = session.get(CartItem.class, new CompositePK(userId, productId));
        session.delete(cartItem);
    }
}
