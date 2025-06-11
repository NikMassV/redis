package edu.mikita.api.repository;

import edu.mikita.api.entity.UserRedisEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<UserRedisEntity, String> {
}
