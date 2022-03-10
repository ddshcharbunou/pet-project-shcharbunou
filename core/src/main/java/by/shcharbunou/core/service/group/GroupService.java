package by.shcharbunou.core.service.group;

import by.shcharbunou.dal.dao.group.GroupDao;
import by.shcharbunou.dal.dao.group.impl.GroupDaoImpl;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("groupService")
public class GroupService {
    private final GroupDao groupDao;

    @Autowired
    public GroupService(GroupDaoImpl groupDao) {
        this.groupDao = groupDao;
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

    public void save(Group group) {
        groupDao.save(group);
    }

    public void update(Group group) {
        groupDao.update(group);
    }

    public void delete(Group group) {
        groupDao.delete(group);
    }

    public List<Group> findAll(Class<Group> groupClass) {
        return groupDao.findAll(groupClass);
    }

    public void deleteAll(Class<Group> groupClass) {
        groupDao.deleteAll(groupClass);
    }
}
