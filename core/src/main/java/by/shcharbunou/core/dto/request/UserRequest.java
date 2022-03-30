package by.shcharbunou.core.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String repeated_password;
}
