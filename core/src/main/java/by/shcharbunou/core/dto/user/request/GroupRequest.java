package by.shcharbunou.core.dto.user.request;

import by.shcharbunou.dal.entity.enums.group.Day;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest {
    private GroupDesignation designation;
    private GroupLevel level;
    private GroupAge age;
    private List<Day> days;
    private String time;
    private String teacher;
    private String additionalInformation;
}
