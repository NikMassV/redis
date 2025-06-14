package edu.mikita.api.service;

import edu.mikita.api.dto.EventDto;
import edu.mikita.api.mapper.EventMapper;
import edu.mikita.api.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventWriteBehindQueueService {

    private final EventRepository eventJpaRepository;
    private final EventMapper eventMapper;

    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public void scheduleWrite(EventDto dto) {
        executor.submit(() -> {
            try {
                log.info("Async DB save for Event id={}", dto.id());
                eventJpaRepository.save(eventMapper.toJpaEntity(dto));
            } catch (Exception e) {
                log.error("Failed to save Event to DB", e);
            }
        });
    }
}
