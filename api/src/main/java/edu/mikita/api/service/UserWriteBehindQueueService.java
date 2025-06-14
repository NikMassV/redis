package edu.mikita.api.service;

import edu.mikita.api.dto.UserDto;
import edu.mikita.api.mapper.UserMapper;
import edu.mikita.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserWriteBehindQueueService {

    private final UserRepository userJpaRepository;
    private final UserMapper userMapper;

    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public void scheduleWrite(UserDto dto) {
        executor.submit(() -> {
            try {
                log.info("Async DB save for User id={}", dto.id());
                userJpaRepository.save(userMapper.toJpaEntity(dto));
            } catch (Exception e) {
                log.error("Failed to save User to DB", e);
            }
        });
    }
}
