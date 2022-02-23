package by.shcharbunou.dal.entity.user;

import by.shcharbunou.dal.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "user_id", nullable = false)
    private UUID id;

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

    @Deprecated
    protected User() {}
}
