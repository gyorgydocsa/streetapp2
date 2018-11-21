package hu.streetartchoir.app.service;

import hu.streetartchoir.app.domain.UserAccount;
import hu.streetartchoir.app.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    private UserAccountRepository userAccountRepository;

    @Autowired
    public JPAUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByEmail(email);

        if (userAccount == null) {
            throw new UsernameNotFoundException("No user found with email: " + email);
        }

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userAccount.getRole().toString());

        UserDetails principal = User.withUsername(email).authorities(authorities).password(userAccount.getPassword()).build();

        return principal;
    }
}


