package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.models.entity.Fertilizer;

/**
 * DTO (Data Transfer Object) para representar um fertilizante.
 */

public record   FertilizerDto(String name, String brand, String composition) {

  public Fertilizer toFertilizer() {
    return new Fertilizer(name, brand, composition);
  }

}
