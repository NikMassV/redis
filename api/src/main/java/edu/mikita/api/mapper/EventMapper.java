package edu.mikita.api.mapper;

import edu.mikita.api.dto.EventDto;
import edu.mikita.api.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDto toDto(Event event);
    Event toEntity(EventDto dto);
}
