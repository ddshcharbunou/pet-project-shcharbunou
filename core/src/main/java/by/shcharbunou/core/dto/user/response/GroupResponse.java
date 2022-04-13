package by.shcharbunou.core.dto.user.response;

import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.enums.group.connector.EmbeddableDay;
import by.shcharbunou.dal.entity.user.User;
import by.shcharbunou.dal.entity.user.office.homework.Homework;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {
    private GroupDesignation designation;
    private GroupLevel level;
    private GroupAge age;
    private List<EmbeddableDay> days;
    private String time;
    private String teacher;
    private Set<User> users;
    private Set<Homework> homeworks;
}
