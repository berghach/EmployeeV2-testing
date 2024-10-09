package service;

import dao.CandidateDAO;
import entities.Candidate;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class CandidateService implements Service<Candidate> {
    private final CandidateDAO candidateDAO = new CandidateDAO();

    @Override
    public Optional<Candidate> get(long id) {
        return candidateDAO.get(id);
    }

    @Override
    public List<Candidate> getAll() {
        return candidateDAO.getAll();
    }

    @Override
    public boolean save(Candidate candidate) throws SystemException {
        return candidateDAO.save(candidate);
    }

    @Override
    public boolean update(Candidate candidate) throws SystemException {
        return candidateDAO.update(candidate);
    }

    @Override
    public boolean delete(Candidate candidate) throws SystemException {
        return candidateDAO.delete(candidate);
    }
}
