package service;

import dao.UserDAO;
import entities.User;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class UserService implements Service<User> {
    private final UserDAO userDAO = new UserDAO();

    @Override
    public Optional<User> get(long id) {
        return userDAO.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public boolean save(User user) throws SystemException {
        return userDAO.save(user);
    }

    @Override
    public boolean update(User user) throws SystemException {
        return userDAO.update(user);
    }

    @Override
    public boolean delete(User user) throws SystemException {
        return userDAO.delete(user);
    }
}
