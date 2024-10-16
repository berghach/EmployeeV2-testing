package service;

import dao.EmployeeChangeDAO;
import entities.Employee;
import entities.EmployeeChange;
import entities.EmployeeChange;
import jakarta.transaction.SystemException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeChangeService implements Service<EmployeeChange> {
    private final EmployeeChangeDAO employeeChangeDAO = new EmployeeChangeDAO();

    @Override
    public Optional<EmployeeChange> get(long id) {
        return employeeChangeDAO.get(id);
    }

    @Override
    public List<EmployeeChange> getAll() {
        try {
            return employeeChangeDAO.getAll();
        } catch (UnsupportedOperationException e) {
            System.out.println("Operation not supported: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<EmployeeChange> getEmployeeChangedFields(Employee oldEmployeeData, Employee newEmployeeData) throws IllegalAccessException {
        List<EmployeeChange> employeeChanges = new ArrayList<>();

        oldEmployeeData.getChangedFieldsReflective(newEmployeeData).forEach((field, values) -> {
            employeeChanges.add(new EmployeeChange(field, values.get(0).toString(), values.get(1).toString(), oldEmployeeData));
        });

        return employeeChanges;
    }

    @Override
    public boolean save(EmployeeChange employeeChange) throws SystemException {
        return employeeChangeDAO.save(employeeChange);
    }

    @Override
    public boolean update(EmployeeChange employeeChange) throws SystemException {
        return employeeChangeDAO.update(employeeChange);
    }

    @Override
    public boolean delete(EmployeeChange employeeChange) throws SystemException {
        return employeeChangeDAO.delete(employeeChange);
    }
}
