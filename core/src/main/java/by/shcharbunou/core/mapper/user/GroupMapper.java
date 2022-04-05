package by.shcharbunou.core.mapper.user;

import by.shcharbunou.core.dto.user.request.GroupRequest;
import by.shcharbunou.core.mapper.user.custom.DayToEmbeddableDayMapper;
import by.shcharbunou.dal.entity.enums.group.Day;
import by.shcharbunou.dal.entity.enums.group.connector.EmbeddableDay;
import by.shcharbunou.dal.entity.user.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    @Mapping(source = "days", target = "days", qualifiedBy = DayToEmbeddableDayMapper.class)
    Group groupRequestToGroup(GroupRequest groupRequest);

    @DayToEmbeddableDayMapper
    static EmbeddableDay dayToEmbeddableDay(Day day) {
        if (day == null) {
            return null;
        }

        EmbeddableDay embeddableDay = new EmbeddableDay();

        if (day.getDay() != null) {
            embeddableDay.setDay(day);
        }

        return embeddableDay;
    }
}
