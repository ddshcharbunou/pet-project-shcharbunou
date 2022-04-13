package by.shcharbunou.core.mapper.user;

import by.shcharbunou.core.dto.user.ClaimDto;
import by.shcharbunou.dal.entity.user.Claim;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClaimMapper {
    Claim claimDtoToClaim(ClaimDto claimDto);
}
