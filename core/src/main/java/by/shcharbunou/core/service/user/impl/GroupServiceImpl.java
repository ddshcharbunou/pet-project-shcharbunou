package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.exception.GroupNotFoundException;
import by.shcharbunou.core.exception.message.GroupMessage;
import by.shcharbunou.core.service.user.GroupService;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.enums.group.connector.EmbeddableDay;
import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.repository.user.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service("groupService")
@Transactional(transactionManager = "transactionManager")
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public Group findGroupByAllParameters(GroupDesignation designation, GroupLevel level, GroupAge age,
                                          List<EmbeddableDay> days, String time) throws GroupNotFoundException {
        Group group = groupRepository.findByDesignationAndLevelAndAgeAndDaysInAndTime(designation, level, age,
                days, time);
        if (Objects.nonNull(group)) {
            return group;
        }
        throw new GroupNotFoundException(GroupMessage.GROUP_NOT_FOUND.getMessage());
    }

    @Override
    public Group findGroupById(UUID id) throws GroupNotFoundException {
        return groupRepository.findById(id).orElseThrow(() ->
                new GroupNotFoundException(GroupMessage.GROUP_NOT_FOUND.getMessage()));
    }

    @Override
    public Group createGroup(HttpServletRequest request, Group group) {
        GroupDesignation designation = GroupDesignation.valueOf(request.getParameter("group_designation"));
        GroupLevel level = GroupLevel.valueOf(request.getParameter("group_level"));
        GroupAge age = GroupAge.valueOf(request.getParameter("group_age"));
        String time = request.getParameter("group_time");
        return null;
    }
}
