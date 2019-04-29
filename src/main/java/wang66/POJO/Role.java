package wang66.POJO;

public class Role {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    private String username;
    private String password;
    private String role;

    @Override
    public String toString() {
        return username+":"+password+":"+role;
    }
}
