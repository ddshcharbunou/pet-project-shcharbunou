package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.dto.user.request.GroupRequest;
import by.shcharbunou.core.exception.*;
import by.shcharbunou.core.exception.message.AdminMessage;
import by.shcharbunou.core.exception.message.GroupMessage;
import by.shcharbunou.core.exception.message.TimeMessage;
import by.shcharbunou.core.exception.message.UserMessage;
import by.shcharbunou.core.mapper.user.GroupMapper;
import by.shcharbunou.core.service.user.GroupService;
import by.shcharbunou.core.service.user.RoleService;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.enums.group.connector.EmbeddableDay;
import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.entity.user.Role;
import by.shcharbunou.dal.entity.user.User;
import by.shcharbunou.dal.repository.user.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Pattern;

@Service("groupService")
@Transactional(transactionManager = "transactionManager")
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final GroupMapper groupMapper;
    private static final Pattern TIME_PATTERN = Pattern.compile("^(([0,1][0-9])|(2[0-3])):[0-5][0-9]$");

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper, UserService userService,
                            RoleService roleService) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.userService = userService;
        this.roleService = roleService;
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
                                          List<EmbeddableDay> days, String time, String teacher) throws GroupNotFoundException {
        Group group = groupRepository.findByDesignationAndLevelAndAgeAndDaysInAndTimeAndTeacher(designation, level, age,
                days, time, teacher);
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
    public Group createGroup(GroupRequest groupRequest) throws TimeFormatException, GroupDuplicateException,
            UserNotFoundException, AdminNotFoundException {
        Group startingGroup = groupMapper.groupRequestToGroup(groupRequest);
        String time = startingGroup.getTime();
        boolean isValidTimeFormat = checkTimeFormat(time);
        if (!isValidTimeFormat) {
            throw new TimeFormatException(TimeMessage.INVALID_TIME_FORMAT.getMessage());
        }
        /*
        boolean isDuplicate = checkGroupDuplicate(startingGroup);
        if (isDuplicate) {
            throw new GroupDuplicateException(GroupMessage.GROUP_DUPLICATE.getMessage());
        }
         */
        boolean isTeacherExists = checkTeacherUsername(startingGroup.getTeacher());
        if (!isTeacherExists) {
            throw new UserNotFoundException(UserMessage.USER_NOT_FOUND.getMessage());
        }
        boolean isTeacherAdmin = checkTeacherRole(startingGroup.getTeacher());
        if (!isTeacherAdmin) {
            throw new AdminNotFoundException(AdminMessage.TEACHER_NOT_ADMIN.getMessage());
        }
        return startingGroup;
    }

    private boolean checkTeacherRole(String username) {
        User teacher;
        Role teacherRole = roleService.findRoleByDesignation(RoleDesignation.ADMIN);
        try {
            teacher = userService.findUserByUsername(username);
        } catch (UserNotFoundException e) {
            return false;
        }
        return teacher.getRole() == teacherRole;
    }

    private boolean checkTeacherUsername(String username) {
        try {
            userService.findUserByUsername(username);
        } catch (UserNotFoundException e) {
            return false;
        }
        return true;
    }

    private boolean checkGroupDuplicate(Group startingGroup) {
        Group testDuplicateGroup = groupRepository.findByDesignationAndLevelAndAgeAndDaysInAndTimeAndTeacher(
                startingGroup.getDesignation(),
                startingGroup.getLevel(),
                startingGroup.getAge(),
                startingGroup.getDays(),
                startingGroup.getTime(),
                startingGroup.getTeacher()
        );
        return Objects.nonNull(testDuplicateGroup);
    }

    private boolean checkTimeFormat(String time) {
        return TIME_PATTERN.matcher(time).matches();
    }
}
