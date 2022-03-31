package by.shcharbunou.dal.entity.user;

import by.shcharbunou.dal.entity.BaseEntity;
import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = "users", callSuper = true)
@AllArgsConstructor

@Entity
@Table(name = "role")
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
public class Role extends BaseEntity {
    @Column(name = "role_designation")
    @Enumerated(EnumType.STRING)
    private RoleDesignation designation;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<>();

    public void connectUser(User user) {
        this.users.add(user);
        user.setRole(this);
    }

    @Deprecated
    public Role() {}
}
