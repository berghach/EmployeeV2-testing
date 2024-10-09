package entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

@Entity
@Table(name = "job_offer")
@NamedQuery(name = "get all jobOffers", query = "SELECT j FROM JobOffer j")
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "posting_date", nullable = false)
    private Timestamp postingDate;

    @Column(name = "validity_duration", nullable = false)
    private Duration validityDuration;

    @Column(name = "valid", columnDefinition = "boolean", nullable = false)
    private boolean valid = true;

    @ManyToOne
    @JoinColumn(
            name = "recruiter_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_job_offer_recruiter"))
    private Recruiter recruiter;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL)
    private List<Candidate> candidates;


    public JobOffer() {
    }

    public JobOffer(String title, String description, Timestamp postingDate, Duration validityDuration, Recruiter recruiter) {
        this.title = title;
        this.description = description;
        this.postingDate = postingDate;
        this.validityDuration = validityDuration;
        this.recruiter = recruiter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Timestamp postingDate) {
        this.postingDate = postingDate;
    }

    public Duration getValidityDuration() {
        return validityDuration;
    }

    public void setValidityDuration(Duration validityDuration) {
        this.validityDuration = validityDuration;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
