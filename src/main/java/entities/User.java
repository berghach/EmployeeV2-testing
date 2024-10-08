package entities;

import enums.UsersRole;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
    //each class in the inheritance hierarchy (parent and child classes) has its own table.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "email", length = 30, unique = true)
    private String email;

    @Column(name = "password", length = 250)
    private String password;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "adresse", length = 50)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UsersRole userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();


    // No-argument constructor for JPA
    public User(){

    }

    public User(String name, String email, String password, String phone, String address, UsersRole userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.userRole = userRole;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UsersRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UsersRole userRole) {
        this.userRole = userRole;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
