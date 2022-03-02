package by.shcharbunou.core.service.group;

import by.shcharbunou.dal.dao.group.GroupDao;
import by.shcharbunou.dal.dao.group.impl.GroupDaoImpl;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.group.Group;

import java.util.List;
import java.util.Objects;

public class GroupService {
    private static GroupService instance = null;
    private final GroupDao groupDao;

    private GroupService() {
        groupDao = GroupDaoImpl.getInstance();
    }

    public static GroupService getInstance() {
        GroupService localInstance = instance;
        if (Objects.isNull(localInstance)) {
            synchronized (GroupService.class) {
                localInstance = instance;
                if (Objects.isNull(localInstance)) {
                    instance = localInstance = new GroupService();
                }
            }
        }
        return localInstance;
    }

    public List<Group> findByDesignation(GroupDesignation designation) {
        return groupDao.findByDesignation(designation);
    }

    public List<Group> findByAge(GroupAge age) {
        return groupDao.findByAge(age);
    }

    public List<Group> findByLevel(GroupLevel level) {
        return groupDao.findByLevel(level);
    }
}
