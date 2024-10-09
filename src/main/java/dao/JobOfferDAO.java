package dao;

import entities.JobOffer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.SystemException;
import util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class JobOfferDAO implements DAO<JobOffer> {
    @Override
    public Optional<JobOffer> get(long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        JobOffer jobOffer = null;
        try {
            jobOffer = entityManager.find(JobOffer.class, id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(jobOffer);
    }

    @Override
    public List<JobOffer> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<JobOffer> jobOffers = null;
        try{
            TypedQuery<JobOffer> userTypedQuery = entityManager.createNamedQuery("get all jobOffers", JobOffer.class);
            jobOffers = userTypedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return jobOffers;
    }

    @Override
    public boolean save(JobOffer jobOffer) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.persist(jobOffer);
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
    public boolean update(JobOffer jobOffer) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.merge(jobOffer);
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
    public boolean delete(JobOffer jobOffer) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            JobOffer managedJobOffer = entityManager.find(JobOffer.class, jobOffer.getId());
            if (managedJobOffer != null) {
                entityManager.remove(managedJobOffer);
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
