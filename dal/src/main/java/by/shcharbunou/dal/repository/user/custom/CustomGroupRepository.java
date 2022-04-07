package by.shcharbunou.dal.repository.user.custom;

import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.enums.group.connector.EmbeddableDay;
import by.shcharbunou.dal.entity.user.Group;

import java.util.List;

public interface CustomGroupRepository {
    Group findByDesignationAndLevelAndAgeAndDaysInAndTimeAndTeacher(GroupDesignation designation, GroupLevel level, GroupAge age,
                                                                    List<EmbeddableDay> days, String time, String teacher);
}
