package dao;

import entities.Holiday;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.SystemException;
import util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class HolidayDAO implements DAO<Holiday> {
    @Override
    public Optional<Holiday> get(long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Holiday holiday = null;
        try {
            holiday = entityManager.find(Holiday.class, id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(holiday);
    }

    @Override
    public List<Holiday> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Holiday> holidays = null;
        try{
            TypedQuery<Holiday> holidayTypedQuery = entityManager.createNamedQuery("get all holidays", Holiday.class);
            holidays = holidayTypedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return holidays;
    }

    @Override
    public boolean save(Holiday holiday) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.persist(holiday);
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
    public boolean update(Holiday holiday) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.merge(holiday);
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
    public boolean delete(Holiday holiday) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            Holiday managedHoliday = entityManager.find(Holiday.class, holiday.getId());
            if (managedHoliday != null) {
                entityManager.remove(managedHoliday);
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
