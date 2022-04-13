package by.shcharbunou.core.dto.user.response;

import by.shcharbunou.dal.entity.user.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String phone;
    private String activationCode;
    private UUID groupClaim;
    private Group group;
}