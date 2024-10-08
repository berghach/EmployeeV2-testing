package entities;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "allowance")
public class Allowance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "transaction", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date transaction;

    @ManyToOne
    @JoinColumn(
            name = "employee_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_employee"))
    private Employee employee;

    public Allowance() {
    }

    public Allowance(Double totalAmount, Date transaction, Employee employee) {
        this.totalAmount = totalAmount;
        this.transaction = transaction;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getTransaction() {
        return transaction;
    }

    public void setTransaction(Date transaction) {
        this.transaction = transaction;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
