package enums;

public enum CandidateStatus {
    RECEIVED("received"),
    ONGOING("ongoing"),
    REJECTED("rejected"),
    ACCEPTED("accepted");

    private final String status;

    CandidateStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
