package by.shcharbunou.dal.entity.role;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "role_id")
    private UUID id;

    @Deprecated
    public Role() {}
}
