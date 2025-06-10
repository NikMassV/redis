package edu.mikita.api.repository;

import edu.mikita.api.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, String> {
}
