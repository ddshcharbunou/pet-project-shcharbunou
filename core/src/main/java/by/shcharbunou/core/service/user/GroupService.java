package by.shcharbunou.core.service.user;

import by.shcharbunou.core.dto.user.request.GroupRequest;
import by.shcharbunou.core.exception.GroupDuplicateException;
import by.shcharbunou.core.exception.GroupNotFoundException;
import by.shcharbunou.core.exception.TimeFormatException;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.enums.group.connector.EmbeddableDay;
import by.shcharbunou.dal.entity.user.Group;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Service for Group entity.
 */
public interface GroupService {
    /**
     * Save group.
     * @param group group
     * @return group {@link Group}
     */
    Group saveGroup(Group group);

    /**
     * Delete group.
     * @param group group
     */
    void deleteGroup(Group group);

    /**
     * Find group by ALL parameters.
     * @param designation designation
     * @param level level
     * @param age age
     * @param days days
     * @param time time
     * @return group {@link Group}
     * @throws GroupNotFoundException group not found
     */
    Group findGroupByAllParameters(GroupDesignation designation, GroupLevel level, GroupAge age,
                                   Collection<List<EmbeddableDay>> days, String time) throws GroupNotFoundException;

    /**
     * Find group by ID.
     * @param id id
     * @return group {@link Group}
     * @throws GroupNotFoundException group not found
     */
    Group findGroupById(UUID id) throws GroupNotFoundException;

    /**
     * Create new group.
     * @param groupRequest group dto
     * @return group {@link Group}
     * @throws TimeFormatException invalid time format
     * @throws GroupDuplicateException group duplicated
     */
    Group createGroup(GroupRequest groupRequest) throws TimeFormatException, GroupDuplicateException;
}
