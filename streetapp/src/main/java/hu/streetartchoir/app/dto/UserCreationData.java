package hu.streetartchoir.app.dto;

import java.util.Objects;

public class UserCreationData {

    private String username;
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreationData userCreationData = (UserCreationData) o;
        return Objects.equals(username, userCreationData.username) &&
                Objects.equals(email, userCreationData.email) &&
                Objects.equals(password, userCreationData.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, email, password);
    }
}
