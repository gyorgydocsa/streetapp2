package hu.streetartchoir.app.service;

import hu.streetartchoir.app.domain.Role;
import hu.streetartchoir.app.domain.UserAccount;
import hu.streetartchoir.app.dto.UserCreationData;
import hu.streetartchoir.app.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserAccountService {

    private UserAccountRepository userAccountRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired

    public UserAccountService(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserCreationData userCreationData){
        String encodedPassword = passwordEncoder.encode(userCreationData.getPassword());
        userCreationData.setPassword(encodedPassword);
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userCreationData.getUsername());
        userAccount.setEmail(userCreationData.getEmail());
        userAccount.setPassword(userCreationData.getPassword());
        userAccount.setProfilePicUrl(" ");
        userAccount.setRole(Role.ROLE_USER);
        userAccountRepository.save(userAccount);
    }
}
