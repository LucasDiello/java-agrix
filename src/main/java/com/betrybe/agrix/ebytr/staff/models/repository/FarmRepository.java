package com.betrybe.agrix.ebytr.staff.models.repository;

import com.betrybe.agrix.ebytr.staff.models.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para gerenciar entidades Farm.
 */

public interface FarmRepository extends JpaRepository<Farm, Long> {

}
