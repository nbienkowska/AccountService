package account.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class BreachedPasswordValidator implements ConstraintValidator<BreachedPasswordValidation, String>
{
    public boolean isValid(String password, ConstraintValidatorContext cxt) {
        List<String> breachedPasswords = Arrays.asList("PasswordForJanuary", "PasswordForFebruary", "PasswordForMarch", "PasswordForApril",
                "PasswordForMay", "PasswordForJune", "PasswordForJuly", "PasswordForAugust",
                "PasswordForSeptember", "PasswordForOctober", "PasswordForNovember", "PasswordForDecember");
        return !breachedPasswords.contains(password);
    }
}
