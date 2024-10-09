package dao;

import entities.EmployeeChange;
import entities.EmployeeChange;
import jakarta.persistence.EntityManager;
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
        return List.of();
    }

    @Override
    public boolean save(EmployeeChange employeeChange) throws SystemException {
        return false;
    }

    @Override
    public boolean update(EmployeeChange employeeChange) throws SystemException {
        return false;
    }

    @Override
    public boolean delete(EmployeeChange employeeChange) throws SystemException {
        return false;
    }
}
