package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.models.entity.Farm;

/**
 * DTO (Data Transfer Object) para representar uma fazenda.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * Converte o DTO FarmDTO em uma entidade Farm.
   *
   * @return a entidade Farm correspondente ao DTO
   */
  public Farm toFarm() {
    return new Farm(id, name, size);
  }
}
