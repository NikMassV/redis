package edu.mikita.api.mapper;

import edu.mikita.api.dto.UserDto;
import edu.mikita.api.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity user);

    UserEntity toJpaEntity(UserDto dto);
}
