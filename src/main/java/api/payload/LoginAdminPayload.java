package api.payload;

public class LoginAdminPayload {

    private String email;
    private String password;
    private String role = "ADMIN";

    public LoginAdminPayload() {
    }

    public LoginAdminPayload(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = "ADMIN";
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
