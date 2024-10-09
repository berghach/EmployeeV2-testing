package entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "holiday")
@NamedQuery(name = "get all holidays", query = "SELECT h FROM Holiday h")
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "message", columnDefinition = "text")
    private String message;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "accepted")
    private Boolean accepted;

    @ManyToOne // Establishes the relationship with the Employee entity
    @JoinColumn(
            name = "employee_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_employee"))
    private Employee employee;

    public Holiday() {
    }

    public Holiday(String subject, String message, Date startDate, Date endDate, Boolean accepted, Employee employee) {
        this.subject = subject;
        this.message = message;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accepted = accepted;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
