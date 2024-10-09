package dao;

import entities.Candidate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.SystemException;
import util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class CandidateDAO implements DAO<Candidate> {
    @Override
    public Optional<Candidate> get(long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Candidate candidate = null;
        try {
            candidate = entityManager.find(Candidate.class, id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(candidate);
    }

    @Override
    public List<Candidate> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Candidate> candidates = null;
        try{
            TypedQuery<Candidate> candidateTypedQuery = entityManager.createNamedQuery("get all candidates", Candidate.class);
            candidates = candidateTypedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return candidates;
    }

    @Override
    public boolean save(Candidate candidate) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.persist(candidate);
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
    public boolean update(Candidate candidate) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.merge(candidate);
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
    public boolean delete(Candidate candidate) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            Candidate managedCandidate = entityManager.find(Candidate.class, candidate.getId());
            if (managedCandidate != null) {
                entityManager.remove(managedCandidate);
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
