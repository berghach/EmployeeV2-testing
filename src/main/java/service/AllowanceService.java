package service;

import dao.AllowanceDAO;
import entities.Allowance;
import jakarta.transaction.SystemException;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public DoubleSummaryStatistics getAllowanceStatistics(Month month) {
        List<Allowance> allowances = getAll();

        List<Allowance> filteredAllowances = allowances.stream()
                .filter(allowance -> {
                    LocalDate transactionDate = allowance.getTransaction().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate();

                    return transactionDate.getMonth() == month;
                })
                .collect(Collectors.toList());

        return filteredAllowances.stream()
                .collect(Collectors.summarizingDouble(Allowance::getTotalAmount));
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
