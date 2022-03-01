package by.shcharbunou.dal.dao.group;

import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.group.Group;

import java.util.List;

public interface GroupDao {
    List<Group> findByDesignation(GroupDesignation designation);

    List<Group> findByAge(GroupAge age);

    List<Group> findByLevel(GroupLevel level);


}
