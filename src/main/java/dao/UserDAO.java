package dao;

import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.SystemException;
import util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User> {
    @Override
    public Optional<User> get(long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        User user = null;
        try {
            user = entityManager.find(User.class, id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<User> users = null;
        try{
            TypedQuery<User> userTypedQuery = entityManager.createNamedQuery("get all users", User.class);
            users = userTypedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return users != null ? users : List.of();
    }

    @Override
    public boolean save(User user) throws SystemException {
        return false;
    }

    @Override
    public boolean update(User user) throws SystemException {
        return false;
    }

    @Override
    public boolean delete(User user) throws SystemException {
        return false;
    }
}
