package entities;

import enums.UsersRole;
import jakarta.persistence.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "employee")
@NamedQuery(name = "get all employees", query = "SELECT e FROM Employee e")
public class Employee extends User{
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "ussn", unique = true)
    private Integer ussn;

    @Column(name = "hiring_date")
    private LocalDate hiringDate;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "holiday_credit")
    private Integer holidayCredit;

    @Column(name = "kids")
    private Integer kids;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false, foreignKey = @ForeignKey(name = "fk_employee_department"))
    private Department department;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false, foreignKey = @ForeignKey(name = "fk_job"))
    private Job job;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Absence> absences;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Allowance> allowances;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Holiday> holidays;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeChange> employeeChanges;

    public Employee() {
        super();
    }

    public Employee(String name, String email, String password, String phone, String address, UsersRole userRole, LocalDate birthDate, Integer ussn, LocalDate hiringDate, Double salary, Integer holidayCredit, Integer kids, Department department, Job job) {
        super(name, email, password, phone, address, userRole);
        this.birthDate = birthDate;
        this.ussn = ussn;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.holidayCredit = holidayCredit;
        this.kids = kids;
        this.department = department;
        this.job = job;
    }


    public Map<String, Object> getChangedFieldsReflective(Employee other) throws IllegalAccessException {
        Map<String, Object> changedFields = new HashMap<>();

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            Object value1 = field.get(this);
            Object value2 = field.get(other);

            if (!Objects.equals(value1, value2)) {
                changedFields.put(field.getName(), value2);
            }
        }
        return changedFields;
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }

    @Override
    public UsersRole getUserRole() {
        return super.getUserRole();
    }

    @Override
    public void setUserRole(UsersRole userRole) {
        super.setUserRole(userRole);
    }

    @Override
    public List<Notification> getNotifications() {
        return super.getNotifications();
    }

    @Override
    public void setNotifications(List<Notification> notifications) {
        super.setNotifications(notifications);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getUssn() {
        return ussn;
    }

    public void setUssn(Integer ussn) {
        this.ussn = ussn;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getHolidayCredit() {
        return holidayCredit;
    }

    public void setHolidayCredit(Integer holidayCredit) {
        this.holidayCredit = holidayCredit;
    }

    public Integer getKids() {
        return kids;
    }

    public void setKids(Integer kids) {
        this.kids = kids;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    public List<Allowance> getAllowances() {
        return allowances;
    }

    public void setAllowances(List<Allowance> allowances) {
        this.allowances = allowances;
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
    }

    public List<EmployeeChange> getEmployeeChanges() {
        return employeeChanges;
    }

    public void setEmployeeChanges(List<EmployeeChange> employeeChanges) {
        this.employeeChanges = employeeChanges;
    }
}
