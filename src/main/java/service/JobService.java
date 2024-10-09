package service;

import dao.JobDAO;
import entities.Job;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class JobService implements Service<Job> {
    private final JobDAO jobDAO = new JobDAO();

    @Override
    public Optional<Job> get(long id) {
        return jobDAO.get(id);
    }

    @Override
    public List<Job> getAll() {
        return jobDAO.getAll();
    }

    @Override
    public boolean save(Job job) throws SystemException {
        return jobDAO.save(job);
    }

    @Override
    public boolean update(Job job) throws SystemException {
        return jobDAO.update(job);
    }

    @Override
    public boolean delete(Job job) throws SystemException {
        return jobDAO.delete(job);
    }
}
