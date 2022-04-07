package by.shcharbunou.dal.repository.user;

import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.enums.group.connector.EmbeddableDay;
import by.shcharbunou.dal.entity.user.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for Group entity.
 */
@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<Group, UUID> {
    /**
     * Find group by all parameters.
     * @param designation designation
     * @param level level
     * @param age age
     * @param days days
     * @param time time
     * @return group {@link Group}
     */
    Group findByDesignationAndLevelAndAgeAndDaysInAndTimeAndTeacher(GroupDesignation designation, GroupLevel level, GroupAge age,
                                                          List<EmbeddableDay> days, String time, String teacher);
}
