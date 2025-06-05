package edu.mikita.api.repository;

import edu.mikita.api.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, String> {}
