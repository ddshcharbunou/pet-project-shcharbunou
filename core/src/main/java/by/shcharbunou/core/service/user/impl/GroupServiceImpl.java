package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.exception.GroupNotFoundException;
import by.shcharbunou.core.exception.message.GroupMessage;
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
        List<EmbeddableDay> days = new ArrayList<>(3);
        if (Objects.nonNull(request.getParameter("monday"))) {
            EmbeddableDay monday = new EmbeddableDay();
            monday.setDay(Day.MONDAY);
            days.add(monday);
        }
        if (Objects.nonNull(request.getParameter("tuesday"))) {
            EmbeddableDay tuesday = new EmbeddableDay();
            tuesday.setDay(Day.TUESDAY);
            days.add(tuesday);
        }
        if (Objects.nonNull(request.getParameter("wednesday"))) {
            EmbeddableDay wednesday = new EmbeddableDay();
            wednesday.setDay(Day.WEDNESDAY);
            days.add(wednesday);
        }
        if (Objects.nonNull(request.getParameter("thursday"))) {
            EmbeddableDay thursday = new EmbeddableDay();
            thursday.setDay(Day.THURSDAY);
            days.add(thursday);
        }
        if (Objects.nonNull(request.getParameter("friday"))) {
            EmbeddableDay friday = new EmbeddableDay();
            friday.setDay(Day.FRIDAY);
            days.add(friday);
        }
        if (Objects.nonNull(request.getParameter("saturday"))) {
            EmbeddableDay saturday = new EmbeddableDay();
            saturday.setDay(Day.SATURDAY);
            days.add(saturday);
        }
        if (Objects.nonNull(request.getParameter("sunday"))) {
            EmbeddableDay sunday = new EmbeddableDay();
            sunday.setDay(Day.SUNDAY);
            days.add(sunday);
        }
        Group startingGroup = new Group();
        startingGroup.setDesignation(designation);
        startingGroup.setLevel(level);
        startingGroup.setAge(age);
        startingGroup.setTime(time);
        startingGroup.setDays(days);
        return startingGroup;
    }
}
