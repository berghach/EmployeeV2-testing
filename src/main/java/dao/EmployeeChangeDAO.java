package dao;

import entities.Employee;
import entities.EmployeeChange;
import entities.EmployeeChange;
import entities.EmployeeChange;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.SystemException;
import util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class EmployeeChangeDAO implements DAO<EmployeeChange> {
    @Override
    public Optional<EmployeeChange> get(long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EmployeeChange employeeChange = null;
        try {
            employeeChange = entityManager.find(EmployeeChange.class, id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(employeeChange);
    }

    @Override
    public List<EmployeeChange> getAll() {
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    @Override
    public boolean save(EmployeeChange employeeChange) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.persist(employeeChange);
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
    public boolean update(EmployeeChange employeeChange) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.merge(employeeChange);
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
    public boolean delete(EmployeeChange employeeChange) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            EmployeeChange managedEmployeeChange = entityManager.find(EmployeeChange.class, employeeChange.getId());
            if (managedEmployeeChange != null) {
                entityManager.remove(managedEmployeeChange);
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
