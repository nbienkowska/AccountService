package account.controllers;

import account.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
public class PaymentController {
    @GetMapping("/api/empl/payment")
    public ResponseEntity<?> checkUser(@AuthenticationPrincipal User auth) {
        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("id", auth.getId());
        responseMap.put("name", auth.getName());
        responseMap.put("lastname", auth.getLastname());
        responseMap.put("email", auth.getEmail());

        return new ResponseEntity(responseMap, HttpStatus.OK);
    }


}
