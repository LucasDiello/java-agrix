package com.betrybe.agrix.ebytr.staff.models.repository;

import com.betrybe.agrix.ebytr.staff.models.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para gerenciar entidades Crop.
 */

public interface CropRepository extends JpaRepository<Crop, Long> {

}
