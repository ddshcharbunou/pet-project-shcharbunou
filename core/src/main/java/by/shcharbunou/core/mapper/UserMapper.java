package by.shcharbunou.core.mapper;

import by.shcharbunou.core.dto.request.UserRequest;
import by.shcharbunou.dal.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRequestToUser(UserRequest userRequest);
}
