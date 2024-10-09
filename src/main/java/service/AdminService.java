package service;

import dao.AdminDAO;
import entities.Admin;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class AdminService implements Service<Admin> {
    private final AdminDAO adminDAO = new AdminDAO();

    @Override
    public Optional<Admin> get(long id) {
        return adminDAO.get(id);
    }

    @Override
    public List<Admin> getAll() {
        return adminDAO.getAll();
    }

    @Override
    public boolean save(Admin admin) throws SystemException {
        return adminDAO.save(admin);
    }

    @Override
    public boolean update(Admin admin) throws SystemException {
        return adminDAO.update(admin);
    }

    @Override
    public boolean delete(Admin admin) throws SystemException {
        return adminDAO.delete(admin);
    }
}
