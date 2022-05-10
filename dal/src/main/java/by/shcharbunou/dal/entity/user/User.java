package by.shcharbunou.dal.entity.user;

import by.shcharbunou.dal.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true, exclude = "group")
@EqualsAndHashCode(exclude = "role", callSuper = false)
@AllArgsConstructor

@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "group_claim")
    private UUID groupClaim;

    @Deprecated
    public User() {}
}
