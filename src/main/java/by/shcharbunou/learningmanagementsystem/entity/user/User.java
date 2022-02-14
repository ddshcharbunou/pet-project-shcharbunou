package by.shcharbunou.learningmanagementsystem.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column
    private UUID id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String password;

    @Deprecated
    protected User() {}
}
