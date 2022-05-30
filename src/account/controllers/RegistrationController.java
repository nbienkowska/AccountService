package account.controllers;

import account.model.User;
import account.payload.ChangePassRequest;
import account.service.UserService;
import account.validation.BreachedPasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody User userRequest) {
        Map<String, Object> responseMap = new HashMap<>();
        String email = userRequest.getEmail().toLowerCase();
        String password = userRequest.getPassword();

        System.out.println("Password: " + password +  "\r\n");
        if (!email.endsWith("@acme.com")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User newUser = new User(userRequest.getName(), userRequest.getLastname(),
                                email, passwordEncoder.encode(password));


        try {
            userService.loadUserByUsername(email);
        } catch (UsernameNotFoundException u) {
            userService.save(newUser);
            responseMap.put("id", newUser.getId());
            responseMap.put("name", newUser.getName());
            responseMap.put("lastname", newUser.getLastname());
            responseMap.put("email", newUser.getEmail());
            return new ResponseEntity(responseMap, HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exist!");
    }

    @PostMapping("/api/auth/changepass")
    public ResponseEntity changePassword(@Valid @RequestBody ChangePassRequest new_password, @AuthenticationPrincipal UserDetails usr) {
        Map<String, Object> responseMap = new HashMap<>();

        if (passwordEncoder.matches(new_password.getNew_password(), usr.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The passwords must be different!");
        }
        String newPassword = passwordEncoder.encode(new_password.getNew_password());
        UserDetails currUsr = userService.loadUserByUsername(usr.getUsername());
        User user = (User) currUsr;
        user.setPassword(newPassword);
        userService.save(user);
        responseMap.put("email", user.getEmail());
        responseMap.put("status", "The password has been updated successfully");
        return new ResponseEntity(responseMap, HttpStatus.OK);
    }
}
