package by.shcharbunou.dal.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Deprecated
    protected User() {}
}
