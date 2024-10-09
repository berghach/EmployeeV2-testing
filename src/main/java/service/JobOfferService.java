package service;

import dao.JobOfferDAO;
import entities.JobOffer;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class JobOfferService implements Service<JobOffer> {
    private final JobOfferDAO jobOfferDAO = new JobOfferDAO();

    @Override
    public Optional<JobOffer> get(long id) {
        return jobOfferDAO.get(id);
    }

    @Override
    public List<JobOffer> getAll() {
        return jobOfferDAO.getAll();
    }

    @Override
    public boolean save(JobOffer jobOffer) throws SystemException {
        return jobOfferDAO.save(jobOffer);
    }

    @Override
    public boolean update(JobOffer jobOffer) throws SystemException {
        return jobOfferDAO.update(jobOffer);
    }

    @Override
    public boolean delete(JobOffer jobOffer) throws SystemException {
        return jobOfferDAO.delete(jobOffer);
    }
}
