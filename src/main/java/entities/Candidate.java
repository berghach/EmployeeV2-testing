package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "candidate")
@NamedQuery(name = "get all candidates", query = "SELECT c FROM Candidate c")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "motivation", columnDefinition = "text")
    private String motivation;

    @Column(name = "file", columnDefinition = "text")
    private String file;

    @ManyToOne
    @JoinColumn(name = "job_offer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_candidate_job_offer"))
    private JobOffer jobOffer;

    public Candidate() {
    }

    public Candidate(String name, String motivation, String file, JobOffer jobOffer) {
        this.name = name;
        this.motivation = motivation;
        this.file = file;
        this.jobOffer = jobOffer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }
}
