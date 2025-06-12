package edu.mikita.api.service;

import edu.mikita.api.dto.UserDto;
import edu.mikita.api.mapper.UserMapper;
import edu.mikita.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto create(UserDto dto) {
        log.info("Saving User to Postgres and Redis");
        UserDto withIdDto = new UserDto(UUID.randomUUID().toString(), dto.name(), dto.age(), dto.events());
        var saved = userRepository.save(userMapper.toJpaEntity(withIdDto));
        return userMapper.toDto(saved);
    }

    @Cacheable(cacheNames = "users", key = "#id", unless = "#result == null")
    public UserDto get(String id) {
        log.info("Cache miss — loading User id={} from Postgres", id);
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    @CachePut(cacheNames = "users", key = "#id")
    public UserDto update(String id, UserDto dto) {
        log.info("Updating User id={} in Postgres and refreshing cache", id);
        dto = new UserDto(id, dto.name(), dto.age(), dto.events());
        var updated = userRepository.save(userMapper.toJpaEntity(dto));
        return userMapper.toDto(updated);
    }

    @CacheEvict(cacheNames = "users", key = "#id")
    public void delete(String id) {
        log.info("Deleting User id={} from Postgres and evicting cache", id);
        userRepository.deleteById(id);
    }
}
