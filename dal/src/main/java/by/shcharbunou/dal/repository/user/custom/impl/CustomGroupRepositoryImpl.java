package by.shcharbunou.dal.repository.user.custom.impl;

import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.enums.group.connector.EmbeddableDay;
import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.repository.user.custom.CustomGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository("groupRepository")
public class CustomGroupRepositoryImpl implements CustomGroupRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CustomGroupRepositoryImpl(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Group findByDesignationAndLevelAndAgeAndDaysInAndTimeAndTeacher(GroupDesignation designation, GroupLevel level,
                                                                           GroupAge age, List<EmbeddableDay> days,
                                                                           String time, String teacher) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);

        Root<Group> group = criteriaQuery.from(Group.class);
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(designation)) {
            predicates.add(criteriaBuilder.equal(group.get("designation"), designation));
        }
        if (Objects.nonNull(level)) {
            predicates.add(criteriaBuilder.equal(group.get("level"), level));
        }
        if (Objects.nonNull(age)) {
            predicates.add(criteriaBuilder.equal(group.get("age"), age));
        }
        if (Objects.nonNull(time)) {
            predicates.add(criteriaBuilder.equal(group.get("time"), time));
        }
        if (Objects.nonNull(teacher)) {
            predicates.add(criteriaBuilder.equal(group.get("teacher"), teacher));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<Group> groupsWithoutDays = entityManager.createQuery(criteriaQuery).getResultList();

        if (groupsWithoutDays.isEmpty()) {
            return null;
        }

        return compareGroupsWithDays(groupsWithoutDays, days);
    }

    private Group compareGroupsWithDays(List<Group> groupsWithoutDays, List<EmbeddableDay> days) {
        Group foundGroup = null;
        for (Group groupWithoutDay : groupsWithoutDays) {
            boolean isSame = checkDaysDuplicate(groupWithoutDay, days);
            if (isSame) {
                foundGroup = groupWithoutDay;
                break;
            }
        }
        return foundGroup;
    }

    private boolean checkDaysDuplicate(Group group, List<EmbeddableDay> days) {
        if (group.getDays().size() != days.size()) {
            return false;
        }
        return compareDays(days, group.getDays());
    }

    private boolean compareDays(List<EmbeddableDay> firstDaysList, List<EmbeddableDay> secondDaysList) {
        boolean isMatches;
        for (EmbeddableDay firstListDay : firstDaysList) {
            isMatches = false;
            for (EmbeddableDay secondListDay : secondDaysList) {
                if (firstListDay.getDay() == secondListDay.getDay()) {
                    isMatches = true;
                    break;
                }
            }
            if (!isMatches) {
                return false;
            }
        }
        return true;
    }
}
