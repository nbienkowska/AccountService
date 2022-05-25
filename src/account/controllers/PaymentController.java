package account.controllers;

import account.model.User;
import account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PaymentController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/empl/payment")
    public ResponseEntity<?> checkUser(@AuthenticationPrincipal User auth) {

        return new ResponseEntity(Map.of("id", auth.getId(), "name", auth.getName(),
                                         "lastname", auth.getLastname(), "email", auth.getEmail()),
                                  HttpStatus.OK);
    }


}
