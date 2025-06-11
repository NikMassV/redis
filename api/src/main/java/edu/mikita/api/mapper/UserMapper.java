package edu.mikita.api.mapper;

import edu.mikita.api.dto.UserDto;
import edu.mikita.api.entity.UserJpaEntity;
import edu.mikita.api.entity.UserRedisEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserRedisEntity userRedisEntity);
    UserRedisEntity toRedisEntity(UserDto dto);

    UserDto toDto(UserJpaEntity user);
    UserJpaEntity toJpaEntity(UserDto dto);
}
