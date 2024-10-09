package service;

import dao.RecruiterDAO;
import entities.Recruiter;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class RecruiterService implements Service<Recruiter>{
    private final RecruiterDAO recruiterDAO = new RecruiterDAO();

    @Override
    public Optional<Recruiter> get(long id) {
        return recruiterDAO.get(id);
    }

    @Override
    public List<Recruiter> getAll() {
        return recruiterDAO.getAll();
    }

    @Override
    public boolean save(Recruiter recruiter) throws SystemException {
        return recruiterDAO.save(recruiter);
    }

    @Override
    public boolean update(Recruiter recruiter) throws SystemException {
        return recruiterDAO.update(recruiter);
    }

    @Override
    public boolean delete(Recruiter recruiter) throws SystemException {
        return recruiterDAO.delete(recruiter);
    }
}
