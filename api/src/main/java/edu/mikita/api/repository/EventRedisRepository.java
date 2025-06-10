package edu.mikita.api.repository;

import edu.mikita.api.entity.EventRedisEntity;
import org.springframework.data.repository.CrudRepository;

public interface EventRedisRepository extends CrudRepository<EventRedisEntity, String> {
}
