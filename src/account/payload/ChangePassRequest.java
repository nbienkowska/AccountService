package account.payload;

import account.validation.BreachedPasswordValidation;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ChangePassRequest {

    @Size(min=12, message = "Password length must be 12 chars minimum!")
    @BreachedPasswordValidation
    String new_password;
}
