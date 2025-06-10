package edu.mikita.api.service;

import edu.mikita.api.dto.EventDto;
import edu.mikita.api.mapper.EventMapper;
import edu.mikita.api.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventDto create(EventDto dto) {
        EventDto withIdDto = new EventDto(UUID.randomUUID().toString(), dto.title(), dto.description());
        var saved = eventRepository.save(eventMapper.toEntity(withIdDto));
        log.info("IN create saved: {}", saved);
        return eventMapper.toDto(saved);
    }

    public EventDto get(String id) {
        return eventRepository.findById(id).map(eventMapper::toDto).orElse(null);
    }

    public EventDto update(String id, EventDto dto) {
        var entity = eventMapper.toEntity(dto);
        entity.setId(id);
        return eventMapper.toDto(eventRepository.save(entity));
    }

    public void delete(String id) {
        eventRepository.deleteById(id);
    }
}
