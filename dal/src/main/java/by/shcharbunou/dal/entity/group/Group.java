package by.shcharbunou.dal.entity.group;

import by.shcharbunou.dal.entity.BaseEntity;
import by.shcharbunou.dal.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor

@Entity
@Table(name = "group")
@AttributeOverride(name = "id", column = @Column(name = "group_id"))
public class Group extends BaseEntity {
    @Column(name = "group_designation")
    private String designation;

    @Column(name = "group_level")
    private String level;

    @Column(name = "group_days")
    private String days;

    @Column(name = "group_time")
    private String time;

    @Column(name = "group_start_date")
    private LocalDateTime start;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    @Deprecated
    public Group() {}
}
