package edu.mikita.api.service;

import edu.mikita.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final CacheManager cacheManager;
    private final UserWriteBehindQueueService writeBehindQueueService;

    private static final String USERS_CACHE = "users";

    public UserDto create(UserDto dto) {
        log.info("Write-Behind: saving User to cache and scheduling DB save");
        UserDto withIdDto = new UserDto(UUID.randomUUID().toString(), dto.name(), dto.age(), dto.events());
        putToCache(withIdDto);
        writeBehindQueueService.scheduleWrite(withIdDto); //delayed recording
        return withIdDto;
    }

    public UserDto get(String id) {
        Cache cache = cacheManager.getCache(USERS_CACHE);
        return cache.get(id, UserDto.class);
    }

    public UserDto update(String id, UserDto dto) {
        var updated = new UserDto(id, dto.name(), dto.age(), dto.events());
        log.info("Write-Behind: updating User in cache and scheduling DB save");
        putToCache(updated);
        writeBehindQueueService.scheduleWrite(updated);
        return updated;
    }

    public void delete(String id) {
        log.info("Evicting User from cache and DB");
        Cache cache = cacheManager.getCache(USERS_CACHE);
        cache.evict(id);
    }

    private void putToCache(UserDto dto) {
        Cache cache = cacheManager.getCache(USERS_CACHE);
        cache.put(dto.id(), dto);
    }
}
