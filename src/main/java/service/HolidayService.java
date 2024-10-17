package service;

import dao.HolidayDAO;
import entities.Holiday;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class HolidayService implements Service<Holiday> {
    private final HolidayDAO holidayDAO;

    public HolidayService(HolidayDAO holidayDAO) {
        this.holidayDAO = holidayDAO;
    }

    @Override
    public Optional<Holiday> get(long id) {
        return holidayDAO.get(id);
    }

    @Override
    public List<Holiday> getAll() {
        return holidayDAO.getAll();
    }

    @Override
    public boolean save(Holiday holiday) throws SystemException {
        return holidayDAO.save(holiday);
    }

    @Override
    public boolean update(Holiday holiday) throws SystemException {
        return holidayDAO.update(holiday);
    }

    @Override
    public boolean delete(Holiday holiday) throws SystemException {
        return holidayDAO.delete(holiday);
    }
}
