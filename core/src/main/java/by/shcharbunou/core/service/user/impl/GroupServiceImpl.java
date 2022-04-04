package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.dto.user.request.GroupRequest;
import by.shcharbunou.core.exception.GroupNotFoundException;
import by.shcharbunou.core.exception.TimeFormatException;
import by.shcharbunou.core.exception.message.GroupMessage;
import by.shcharbunou.core.exception.message.TimeMessage;
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
    private static final Pattern TIME_PATTERN = Pattern.compile("^(([0,1][0-9])|(2[0-3])):[0-5][0-9]$");

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
    public Group createGroup(GroupRequest groupRequest) throws TimeFormatException {
        GroupDesignation designation = groupRequest.getDesignation();
        GroupLevel level = groupRequest.getLevel();
        GroupAge age = groupRequest.getAge();
        String time = groupRequest.getTime();
        boolean isValidTimeFormat = checkTimeFormat(time);
        if (!isValidTimeFormat) {
            throw new TimeFormatException(TimeMessage.INVALID_TIME_FORMAT.getMessage());
        }
        List<EmbeddableDay> days = new ArrayList<>(3);
        if (Objects.nonNull(groupRequest.getMonday())) {
            EmbeddableDay monday = new EmbeddableDay();
            monday.setDay(Day.MONDAY);
            days.add(monday);
        }
        if (Objects.nonNull(groupRequest.getTuesday())) {
            EmbeddableDay tuesday = new EmbeddableDay();
            tuesday.setDay(Day.TUESDAY);
            days.add(tuesday);
        }
        if (Objects.nonNull(groupRequest.getWednesday())) {
            EmbeddableDay wednesday = new EmbeddableDay();
            wednesday.setDay(Day.WEDNESDAY);
            days.add(wednesday);
        }
        if (Objects.nonNull(groupRequest.getThursday())) {
            EmbeddableDay thursday = new EmbeddableDay();
            thursday.setDay(Day.THURSDAY);
            days.add(thursday);
        }
        if (Objects.nonNull(groupRequest.getFriday())) {
            EmbeddableDay friday = new EmbeddableDay();
            friday.setDay(Day.FRIDAY);
            days.add(friday);
        }
        if (Objects.nonNull(groupRequest.getSaturday())) {
            EmbeddableDay saturday = new EmbeddableDay();
            saturday.setDay(Day.SATURDAY);
            days.add(saturday);
        }
        if (Objects.nonNull(groupRequest.getSunday())) {
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

    private boolean checkTimeFormat(String time) {
        return TIME_PATTERN.matcher(time).matches();
    }
}
