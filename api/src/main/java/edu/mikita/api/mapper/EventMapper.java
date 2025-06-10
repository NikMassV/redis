package edu.mikita.api.mapper;

import edu.mikita.api.dto.EventDto;
import edu.mikita.api.entity.EventJpaEntity;
import edu.mikita.api.entity.EventRedisEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto toDto(EventRedisEntity eventRedisEntity);
    EventRedisEntity toRedisEntity(EventDto dto);

    EventDto toDto(EventJpaEntity event);
    EventJpaEntity toJpaEntity(EventDto dto);
}
