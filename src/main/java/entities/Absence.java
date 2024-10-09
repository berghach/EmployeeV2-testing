package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "absence")
@NamedQuery(name = "get all absences", query = "SELECT a FROM Absence a")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "absence_date")
    private Date absenceDate;

    @Column(name = "days")
    private Integer days;

    @Column(name = "justified")
    private boolean justified;

    @Column(name = "file")
    private String file;

    @ManyToOne
    @JoinColumn(
            name = "employee_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_employee"))
    private Employee employee;

    public Absence() {
    }

    public Absence(Date absenceDate, Integer days, boolean justified, String file, Employee employee) {
        this.absenceDate = absenceDate;
        this.days = days;
        this.justified = justified;
        this.file = file;
        this.employee = employee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getAbsenceDate() {
        return absenceDate;
    }

    public void setAbsenceDate(Date absenceDate) {
        this.absenceDate = absenceDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public boolean isJustified() {
        return justified;
    }

    public void setJustified(boolean justified) {
        this.justified = justified;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
