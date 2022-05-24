package account.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User implements UserDetails {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     @Getter
     @Setter
     @NotEmpty
     String name;

     @Getter
     @Setter
     @NotEmpty
     String lastname;

    // @Getter
     @Setter
     @NotEmpty
     String email;

     @Getter
     @Setter
     @NotEmpty
     private String password;

     @Getter
     @Setter
     @NotEmpty
     String username;

     private boolean accountNonExpired;

     private boolean accountNonLocked;

     private boolean credentialsNonExpired;

     private boolean enabled;

     //@Enumerated(EnumType.STRING)
     @ElementCollection(fetch = FetchType.EAGER)
     private List<Role> roles;

     public User() {};

     public User(String name, String lastname, String email, String password) {
          this.name = name;
          this.lastname = lastname;
          this.email = email;
          this.username = email;
          this.password = password;
          this.accountNonExpired = true;
          this.accountNonLocked = true;
          this.credentialsNonExpired = true;
          this.enabled = true;
     }

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public String getEmail() { return email;}

     @Override
     public boolean isAccountNonExpired() {
          return accountNonExpired;
     }

     @Override
     public boolean isAccountNonLocked() {
          return accountNonLocked;
     }

     @Override
     public boolean isCredentialsNonExpired() {
          return credentialsNonExpired;
     }

     @Override
     public boolean isEnabled() {
          return true;
     }

     public void grantAuthority(Role authority) {
          if ( roles == null ) roles = new ArrayList<>();
          roles.add(authority);
     }

     @Override
     public List<GrantedAuthority> getAuthorities(){
          List<GrantedAuthority> authorities = new ArrayList<>();
          roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
          return authorities;
     }

}
