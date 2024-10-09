package dao;

import entities.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.SystemException;
import util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class DepartmentDAO implements DAO<Department>{
    @Override
    public Optional<Department> get(long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Department department = null;
        try {
            department = entityManager.find(Department.class, id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(department);
    }

    @Override
    public List<Department> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Department> departments = null;
        try{
            TypedQuery<Department> departmentTypedQuery = entityManager.createNamedQuery("get all departments", Department.class);
            departments = departmentTypedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return departments;
    }

    @Override
    public boolean save(Department department) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.persist(department);
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
    public boolean update(Department department) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            entityManager.merge(department);
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
    public boolean delete(Department department) throws SystemException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean success = false;

        try {
            transaction.begin();
            Department managedDepartment = entityManager.find(Department.class, department.getId());
            if (managedDepartment != null) {
                entityManager.remove(managedDepartment);
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
