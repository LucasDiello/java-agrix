package com.betrybe.agrix.ebytr.staff.models.repository;

import com.betrybe.agrix.ebytr.staff.models.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para gerenciar entidades Fertilizer.
 */

public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

}
