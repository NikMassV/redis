package edu.mikita.api.repository;

import edu.mikita.api.entity.EventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<EventJpaEntity, String> {
}
