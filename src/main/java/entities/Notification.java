package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String subject;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @ManyToOne
    @JoinColumn(
            name = "users_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_notification_users"))
    private User user;

    public Notification(){

    }

    public Notification(String subject, String message, User user) {
        this.subject = subject;
        this.message = message;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
