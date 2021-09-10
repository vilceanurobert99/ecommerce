package com.ecommerce.repositories.implementations;

import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.User;
import com.ecommerce.models.UserCreditCard;
import com.ecommerce.repositories.specifications.UserCreditCardRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCreditCardRepositoryImplementation implements UserCreditCardRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserCreditCardRepositoryImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(UserCreditCard creditCard, Long userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        if(user == null) {
            throw new NotFoundException();
        }
        creditCard.setUserId(userId);
        session.saveOrUpdate(creditCard);
    }

    @Override
    public UserCreditCard findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        UserCreditCard creditCard = session.get(UserCreditCard.class, id);
        if (creditCard == null) {
            throw new NotFoundException();
        }
        return creditCard;
    }

    @Override
    public boolean existsById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        UserCreditCard creditCard = session.get(UserCreditCard.class, id);
        return creditCard != null;
    }

    @Override
    public void deleteSpecificCard(Long cardId) {
        Session session = sessionFactory.getCurrentSession();
        UserCreditCard creditCard = session.get(UserCreditCard.class, cardId);
        session.delete(creditCard);
    }

    @Override
    public void delete(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserCreditCard> query = session.createQuery("DELETE FROM UserCreditCard WHERE userId =: userId");
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Override
    public List<UserCreditCard> findUserCreditCards(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserCreditCard> query = session.createQuery("FROM UserCreditCard WHERE userId =: userId");
        query.setParameter("userId", userId);
        return query.list();
    }
}
