package service;

import dao.EmployeeDAO;
import entities.Employee;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class EmployeeService implements Service<Employee> {
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Optional<Employee> get(long id) {
        return employeeDAO.get(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    @Override
    public boolean save(Employee employee) throws SystemException {
        return employeeDAO.save(employee);
    }

    @Override
    public boolean update(Employee employee) throws SystemException {
        return employeeDAO.update(employee);
    }

    @Override
    public boolean delete(Employee employee) throws SystemException {
        return employeeDAO.delete(employee);
    }
}
