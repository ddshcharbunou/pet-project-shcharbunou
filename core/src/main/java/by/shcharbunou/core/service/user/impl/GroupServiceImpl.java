package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.dto.user.request.GroupRequest;
import by.shcharbunou.core.exception.GroupNotFoundException;
import by.shcharbunou.core.exception.TimeFormatException;
import by.shcharbunou.core.exception.message.GroupMessage;
import by.shcharbunou.core.exception.message.TimeMessage;
import by.shcharbunou.core.mapper.user.GroupMapper;
import by.shcharbunou.core.service.user.GroupService;
import by.shcharbunou.dal.entity.enums.group.Day;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Service("groupService")
@Transactional(transactionManager = "transactionManager")
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private static final Pattern TIME_PATTERN = Pattern.compile("^(([0,1][0-9])|(2[0-3])):[0-5][0-9]$");

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
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
    public Group createGroup(GroupRequest groupRequest) throws TimeFormatException {
        Group startingGroup = groupMapper.groupRequestToGroup(groupRequest);
        String time = startingGroup.getTime();
        boolean isValidTimeFormat = checkTimeFormat(time);
        if (!isValidTimeFormat) {
            throw new TimeFormatException(TimeMessage.INVALID_TIME_FORMAT.getMessage());
        }
        return startingGroup;
    }

    private boolean checkTimeFormat(String time) {
        return TIME_PATTERN.matcher(time).matches();
    }
}
