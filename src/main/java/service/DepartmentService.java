package service;

import dao.DepartmentDAO;
import entities.Department;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class DepartmentService implements Service<Department> {
    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    public Optional<Department> get(long id) {
        return departmentDAO.get(id);
    }

    @Override
    public List<Department> getAll() {
        return departmentDAO.getAll();
    }

    @Override
    public boolean save(Department department) throws SystemException {
        return departmentDAO.save(department);
    }

    @Override
    public boolean update(Department department) throws SystemException {
        return departmentDAO.update(department);
    }

    @Override
    public boolean delete(Department department) throws SystemException {
        return departmentDAO.delete(department);
    }
}
