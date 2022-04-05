package by.shcharbunou.core.mapper.user;

import by.shcharbunou.core.dto.user.request.GroupRequest;
import by.shcharbunou.dal.entity.user.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    Group groupRequestToGroup(GroupRequest groupRequest);
}
