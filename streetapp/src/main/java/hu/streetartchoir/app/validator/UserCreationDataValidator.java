package hu.streetartchoir.app.validator;

import hu.streetartchoir.app.domain.UserAccount;
import hu.streetartchoir.app.dto.UserCreationData;
import hu.streetartchoir.app.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserCreationDataValidator implements Validator {

    private UserAccountRepository userAccountRepository;

    @Autowired
    public UserCreationDataValidator(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCreationData.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreationData form = (UserCreationData) target;
        UserAccount userAccount = userAccountRepository.findByEmail(form.getEmail());

        if (userAccount != null) {
            errors.rejectValue("email", "email.emailAlreadyExists");
        }

        if (form.getUsername().isEmpty()) {
            errors.rejectValue("username", "username.empty");
        }
        if (form.getUsername().length() < 3) {
            errors.rejectValue("username", "username.short");
        }

        if (form.getEmail().isEmpty()) {
            errors.rejectValue("email", "email.empty");
        }

        Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regMatcher = regexPattern.matcher(form.getEmail());
        if (!regMatcher.matches()) {
            errors.rejectValue("email", "email.is.invalid");
        }

        if (form.getPassword().isEmpty()) {
            errors.rejectValue("password", "password.empty");
        }

        if (form.getPassword().length() < 3) {
            errors.rejectValue("password", "password.short");
        }
    }
}
