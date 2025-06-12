package edu.mikita.api.mapper;

import edu.mikita.api.dto.EventDto;
import edu.mikita.api.entity.EventEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto toDto(EventEntity event);

    EventEntity toJpaEntity(EventDto dto);
}
