package com.betrybe.agrix.ebytr.staff.models.repository;

import com.betrybe.agrix.ebytr.staff.models.entity.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Person JPA repository.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

  Optional<Person> findByUsername(String username);
}
