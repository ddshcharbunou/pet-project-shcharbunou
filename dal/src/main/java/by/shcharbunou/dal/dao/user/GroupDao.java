package by.shcharbunou.dal.dao.user;

import by.shcharbunou.dal.dao.BaseDao;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.user.Group;

import java.util.List;

public interface GroupDao extends BaseDao<Group> {
    List<Group> findByDesignation(GroupDesignation designation);

    List<Group> findByAge(GroupAge age);

    List<Group> findByLevel(GroupLevel level);
}
