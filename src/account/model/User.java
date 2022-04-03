package account.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

public class User {
     @Getter
     @Setter
     @NotEmpty
     String name;

     @Getter
     @Setter
     @NotEmpty
     String lastname;

     @Getter
     @Setter
     @NotEmpty
     String email;

     @Getter
     @Setter
     @NotEmpty
     String password;

     public User() {};

     public User(String name, String lastname, String email, String password) {
          this.name = name;
          this.lastname = lastname;
          this.email = email;
          this.password = password;
     }
}
