package com.cashrich.backend.DAO.Impl;

import com.cashrich.backend.DAO.UserDao;
import com.cashrich.backend.Entity.RequestResponseLogs;
import com.cashrich.backend.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        String query = "SELECT u FROM User u WHERE u.email = :email";
        List<User> users = entityManager.createQuery(query, User.class)
                .setParameter("email", email)
                .getResultList();
        return users.stream().findFirst();
    }

    @Override
    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public RequestResponseLogs saveResponse(RequestResponseLogs requestResponseLogs) {
        entityManager.persist(requestResponseLogs);
        return requestResponseLogs;
    }
}
