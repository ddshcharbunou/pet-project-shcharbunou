package by.shcharbunou.dal.entity.user;

import by.shcharbunou.dal.entity.BaseEntity;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import javax.persistence.*;

import by.shcharbunou.dal.entity.enums.group.connector.EmbeddableDay;
import by.shcharbunou.dal.entity.user.office.homework.Homework;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @Enumerated(EnumType.STRING)
    private GroupDesignation designation;

    @Column(name = "group_level")
    @Enumerated(EnumType.STRING)
    private GroupLevel level;

    @Column(name = "group_age")
    @Enumerated(EnumType.STRING)
    private GroupAge age;

    @Column(name = "group_days")
    @ElementCollection
    private List<EmbeddableDay> days = new ArrayList<>(7);

    @Column(name = "group_time")
    private String time;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Homework> homeworks = new HashSet<>();

    public void addHomework(Homework homework) {
        this.homeworks.add(homework);
        homework.setGroup(this);
    }

    public void deleteHomework(Homework homework) {
        this.homeworks.remove(homework);
    }

    public void connectUser(User user) {
        this.users.add(user);
        user.setGroup(this);
    }

    public void disconnectUser(User user) {
        this.users.remove(user);
        user.setGroup(null);
    }

    @Deprecated
    public Group() {}
}
