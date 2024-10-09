package service;

import dao.AbsenceDAO;
import entities.Absence;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class AbsenceService implements Service<Absence> {
    private final AbsenceDAO absenceDAO = new AbsenceDAO();

    @Override
    public Optional<Absence> get(long id) {
        return absenceDAO.get(id);
    }

    @Override
    public List<Absence> getAll() {
        return absenceDAO.getAll();
    }

    @Override
    public boolean save(Absence absence) throws SystemException {
        return absenceDAO.save(absence);
    }

    @Override
    public boolean update(Absence absence) throws SystemException {
        return absenceDAO.update(absence);
    }

    @Override
    public boolean delete(Absence absence) throws SystemException {
        return absenceDAO.delete(absence);
    }
}
