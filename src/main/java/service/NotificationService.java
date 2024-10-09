package service;

import dao.NotificationDAO;
import entities.Notification;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class NotificationService implements Service<Notification> {
    private final NotificationDAO notificationDAO = new NotificationDAO();

    @Override
    public Optional<Notification> get(long id) {
        return notificationDAO.get(id);
    }

    @Override
    public List<Notification> getAll() {
        return notificationDAO.getAll();
    }

    @Override
    public boolean save(Notification notification) throws SystemException {
        return notificationDAO.save(notification);
    }

    @Override
    public boolean update(Notification notification) throws SystemException {
        return notificationDAO.update(notification);
    }

    @Override
    public boolean delete(Notification notification) throws SystemException {
        return notificationDAO.delete(notification);
    }
}
