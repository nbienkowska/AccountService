package account.controllers;

import account.model.User;
import account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/api/auth/signup")
    public ResponseEntity<User> createUser(@Valid @RequestBody User userRequest) {
        Map<String, Object> responseMap = new HashMap<>();
        String email = userRequest.getEmail().toLowerCase();
        User newUser = new User(userRequest.getName(), userRequest.getLastname(),
                                email, userRequest.getPassword());

        if (!email.endsWith("@acme.com")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

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
}
