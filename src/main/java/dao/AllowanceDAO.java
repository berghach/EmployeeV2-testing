package dao;

import entities.Allowance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.SystemException;
import util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class AllowanceDAO implements DAO<Allowance> {
    @Override
    public Optional<Allowance> get(long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Allowance allowance = null;
        try {
            allowance = entityManager.find(Allowance.class, id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(allowance);
    }

    @Override
    public List<Allowance> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Allowance> allowances = null;
        try{
            TypedQuery<Allowance> allowanceTypedQuery = entityManager.createNamedQuery("get all allowances", Allowance.class);
            allowances = allowanceTypedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return allowances;
    }

    @Override
    public boolean save(Allowance allowance) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.persist(allowance);
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
    public boolean update(Allowance allowance) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.merge(allowance);
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
    public boolean delete(Allowance allowance) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            Allowance managedAllowance = entityManager.find(Allowance.class, allowance.getId());
            if (managedAllowance != null) {
                entityManager.remove(managedAllowance);
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
