package by.shcharbunou.dal.entity.role;

import by.shcharbunou.dal.entity.enums.RoleDesignation;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor

@Entity
@Table(name = "role")
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
public class Role {
    @Column(name = "role_designation")
    @Enumerated(EnumType.STRING)
    private RoleDesignation roleDesignation;

    @Deprecated
    public Role() {}
}
