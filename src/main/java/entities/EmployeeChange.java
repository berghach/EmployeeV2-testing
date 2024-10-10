package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_change")
public class EmployeeChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "field", length = 30, nullable = false)
    private String field;

    @Column(name = "old_value", length = 30)
    private String oldValue;

    @Column(name = "new_value", length = 30)
    private String newValue;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, foreignKey = @ForeignKey(name = "fk_employee"))
    private Employee employee;

    public EmployeeChange() {
    }

    public EmployeeChange(String field, String oldValue, String newValue, Employee employee) {
        this.field = field;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
