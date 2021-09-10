package com.ecommerce.repositories.implementations;

import com.ecommerce.exceptions.NotAllowedException;
import com.ecommerce.models.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryRepositoryImplementation {
    private final SessionFactory sessionFactory;

    public CategoryRepositoryImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Category getCategoryById(Long categoryId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Category.class, categoryId);
    }

    public void addCategory(String categoryName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Category> query = session.createQuery("FROM Category WHERE name =: categoryName");

        query.setParameter("categoryName", categoryName);
        if(query.list().size() > 0) {
            throw new NotAllowedException();
        } else {
            Category category = new Category();
            category.setName(categoryName);
            session.saveOrUpdate(category);
        }
    }
}
