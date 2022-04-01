package by.shcharbunou.core.mapper.user;

import by.shcharbunou.core.dto.user.request.UserRequest;
import by.shcharbunou.core.dto.user.response.UserResponse;
import by.shcharbunou.dal.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRequestToUser(UserRequest userRequest);

    UserResponse userToUserResponse(User user);
}
