package hu.streetartchoir.app.controller;

import hu.streetartchoir.app.dto.UserCreationData;
import hu.streetartchoir.app.security.AuthenticatedUserDetails;
import hu.streetartchoir.app.service.UserAccountService;
import hu.streetartchoir.app.validator.UserCreationDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    private UserAccountService userAccountService;
    private UserCreationDataValidator userCreationDataValidator;

    @Autowired
    public UserAccountController(UserAccountService userAccountService, UserCreationDataValidator userCreationDataValidator1) {
        this.userAccountService = userAccountService;
        this.userCreationDataValidator = userCreationDataValidator1;
    }


    @InitBinder("userCreationData")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreationDataValidator);
    }

    @PostMapping
    public ResponseEntity saveUser(@Valid @RequestBody UserCreationData userCreationData) {
        userAccountService.createUser(userCreationData);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<AuthenticatedUserDetails> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return new ResponseEntity<>(new AuthenticatedUserDetails(user), HttpStatus.OK);
    }


}
