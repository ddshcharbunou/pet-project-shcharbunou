package by.shcharbunou.dal.repository.user.custom.impl;

import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.enums.group.connector.EmbeddableDay;
import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.repository.user.custom.CustomGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public class CustomGroupRepositoryImpl implements CustomGroupRepository {
    private final EntityManager em;

    @Autowired
    public CustomGroupRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Group findByDesignationAndLevelAndAgeAndDaysInAndTimeAndTeacher(GroupDesignation designation, GroupLevel level, GroupAge age, List<EmbeddableDay> days, String time, String teacher) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        return null;
    }
}
