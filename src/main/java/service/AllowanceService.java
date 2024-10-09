package service;

import dao.AllowanceDAO;
import entities.Allowance;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class AllowanceService implements Service<Allowance> {
    private final AllowanceDAO allowanceDAO = new AllowanceDAO();

    @Override
    public Optional<Allowance> get(long id) {
        return allowanceDAO.get(id);
    }

    @Override
    public List<Allowance> getAll() {
        return allowanceDAO.getAll();
    }

    @Override
    public boolean save(Allowance allowance) throws SystemException {
        return allowanceDAO.save(allowance);
    }

    @Override
    public boolean update(Allowance allowance) throws SystemException {
        return allowanceDAO.update(allowance);
    }

    @Override
    public boolean delete(Allowance allowance) throws SystemException {
        return allowanceDAO.delete(allowance);
    }
}
