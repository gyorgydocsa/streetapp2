package hu.streetartchoir.app.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class AuthenticatedUserDetails {
    private String emailAddress;
    private String role;

    public AuthenticatedUserDetails() {
    }

    public AuthenticatedUserDetails(UserDetails user) {
        this.emailAddress = user.getUsername();
        this.role = findRole(user);
    }

    private String findRole(UserDetails user) {
        String role = null;
        List<GrantedAuthority> roles = user.getAuthorities().stream()
                .filter(authority -> authority.getAuthority().startsWith("ROLE_"))
                .collect(Collectors.toList());
        if (!roles.isEmpty()) {
            role = roles.get(0).getAuthority();
        }
        return role;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
