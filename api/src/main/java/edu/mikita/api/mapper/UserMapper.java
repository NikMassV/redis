package edu.mikita.api.mapper;

import edu.mikita.api.dto.UserDto;
import edu.mikita.api.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto dto);
}
