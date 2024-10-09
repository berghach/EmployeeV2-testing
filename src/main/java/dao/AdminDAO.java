package dao;

import entities.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.SystemException;
import util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class AdminDAO implements DAO<Admin> {
    @Override
    public Optional<Admin> get(long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Admin admin = null;
        try {
            admin = entityManager.find(Admin.class, id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(admin);
    }

    @Override
    public List<Admin> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Admin> admins = null;
        try{
            TypedQuery<Admin> userTypedQuery = entityManager.createNamedQuery("get all admins", Admin.class);
            admins = userTypedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return admins;
    }

    @Override
    public boolean save(Admin admin) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.persist(admin);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return success;
    }

    @Override
    public boolean update(Admin admin) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.merge(admin);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return success;
    }

    @Override
    public boolean delete(Admin admin) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            Admin managedAdmin = entityManager.find(Admin.class, admin.getId());
            if (managedAdmin != null) {
                entityManager.remove(managedAdmin);
                transaction.commit();
                success = true;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return success;
    }
}
