package account.controllers;

import account.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegistrationController {

@PostMapping("/api/auth/signup")
public ResponseEntity<?> createUser(@RequestBody User userRequest) {
    Map<String, Object> responseMap = new HashMap<>();
    String name = userRequest.getName();
    String lastname = userRequest.getLastname();
    String email = userRequest.getEmail();
    String password = userRequest.getPassword();

    if (!email.endsWith("@acme.com") || "".equals(name) || name == null
            || "".equals(lastname) || lastname == null || "".equals(password) || password == null) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        responseMap.put("timestamp", timestamp.toString());
        responseMap.put("status", 400);
        responseMap.put("error", "Bad Request");
        responseMap.put("path", "/api/auth/signup");
        return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
    }

    responseMap.put("name", userRequest.getName());
    responseMap.put("lastname", userRequest.getLastname());
    responseMap.put("email", userRequest.getEmail());


    return new ResponseEntity(responseMap, HttpStatus.OK);

}
}
