package dao;

import entities.Recruiter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.SystemException;
import util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class RecruiterDAO implements DAO<Recruiter>{
    @Override
    public Optional<Recruiter> get(long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Recruiter recruiter = null;
        try {
            recruiter = entityManager.find(Recruiter.class, id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(recruiter);
    }

    @Override
    public List<Recruiter> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Recruiter> recruiters = null;
        try{
            TypedQuery<Recruiter> userTypedQuery = entityManager.createNamedQuery("get all recruiters", Recruiter.class);
            recruiters = userTypedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return recruiters;
    }

    @Override
    public boolean save(Recruiter recruiter) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.persist(recruiter);
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
    public boolean update(Recruiter recruiter) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.merge(recruiter);
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
    public boolean delete(Recruiter recruiter) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            Recruiter managedRecruiter = entityManager.find(Recruiter.class, recruiter.getId());
            if (managedRecruiter != null) {
                entityManager.remove(managedRecruiter);
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
