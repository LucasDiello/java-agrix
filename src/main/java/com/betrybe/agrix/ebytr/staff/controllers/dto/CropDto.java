package com.betrybe.agrix.ebytr.staff.controllers.dto;


import com.betrybe.agrix.ebytr.staff.models.entity.Crop;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) para representar uma plantação.
 */

@JsonPropertyOrder({"id", "name", "plantedArea", "plantedDate", "harvestDate", "farmId"})
public record CropDto(Long id, String name, Double plantedArea,
                      LocalDate plantedDate, LocalDate harvestDate, Long farmId) {
  public Crop toCrop() {
    return new Crop(id, name, plantedArea, plantedDate, harvestDate);
  }

}