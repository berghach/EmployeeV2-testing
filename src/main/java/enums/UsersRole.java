package enums;

public enum UsersRole {
    ADMIN("admin"),
    EMPLOYEE("employee"),
    RECRUITER("recruiter");

    private final String role;

    UsersRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
