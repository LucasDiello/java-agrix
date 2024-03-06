package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.exception.advice.NotFoundException;
import com.betrybe.agrix.ebytr.staff.models.entity.Crop;
import com.betrybe.agrix.ebytr.staff.models.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para manipulação de plantações.
 */

@RestController
@RequestMapping("/crops")
public class CropController {

  private final FarmService farmService;

  private final FertilizerService fertilizerService;

  @Autowired
  public CropController(FarmService farmService, FertilizerService fertilizerService) {
    this.farmService = farmService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Retorna uma lista de todas as plantações cadastradas.
   *
   * @return ResponseEntity contendo uma lista de CropDto representando todas as plantações
   */

  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> crops = farmService.getAllCrops();
    List<CropDto> responseCropDto = crops.stream().map(crop ->
        new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId()
        )
    ).toList();
    return ResponseEntity.ok(responseCropDto);
  }

  /**
   * Verifica se uma determinada data está dentro de um intervalo de datas, incluindo os limites.
   *
   * @param date  A data a ser verificada.
   * @param start A data de início do intervalo.
   * @param end   A data de término do intervalo.
   * @return true se a data estiver dentro do intervalo (incluindo os limites),
   */

  private boolean verifyDate(LocalDate date, LocalDate start, LocalDate end) {
    return (date.isAfter(start) || date.isEqual(start)) && (date.isBefore(end) || date.isEqual(
        end));
  }

  /**
   * Obtém as plantações dentro de um intervalo de datas.
   *
   * @param start A data de início do intervalo.
   * @param end   A data de fim do intervalo.
   * @return Uma lista de objetos CropDto que correspondem ao intervalo de datas fornecido.
   */

  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropsFilterDate(
      @RequestParam("start") LocalDate start,
      @RequestParam("end") LocalDate end
  ) {
    List<Crop> crops = farmService.getAllCrops();
    List<CropDto> responseCropDto = crops.stream().filter(crop ->
        verifyDate(crop.getHarvestDate(), start, end)
    ).map(crop ->
        new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId()
        )
    ).toList();

    return ResponseEntity.ok().body(responseCropDto);
  }

  /**
   * Obtém uma plantação pelo seu ID.
   *
   * @param cropId o ID da plantação a ser obtida
   * @return ResponseEntity contendo a CropDto representando a plantação obtida
   * @throws NotFoundException se a plantação não for encontrada
   */

  @GetMapping("/{cropId}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long cropId) {
    Optional<Crop> crop = farmService.getCropById(cropId);
    if (crop.isEmpty()) {
      throw new NotFoundException("Plantação não encontrada!");
    }

    Crop responseCrop = crop.get();
    CropDto responseCropDto = new CropDto(
        responseCrop.getId(),
        responseCrop.getName(),
        responseCrop.getPlantedArea(),
        responseCrop.getPlantedDate(),
        responseCrop.getHarvestDate(),
        responseCrop.getFarm().getId()
    );
    return ResponseEntity.ok().body(responseCropDto);
  }

  /**
   * Endpoint para associar um fertilizante a uma plantação.
   *
   * @param cropId       O ID da plantação
   * @param fertilizerId O ID do fertilizante
   * @return Um ResponseEntity com uma mensagem de sucesso se a associação.
   */

  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropWithFertilizer(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId) {

    Optional<Crop> optionalCrop = farmService.getCropById(cropId);
    if (optionalCrop.isEmpty()) {
      throw new NotFoundException("Plantação não encontrada!");
    }
    Optional<Fertilizer> optionalFertilizer = fertilizerService.findByIdFertilizer(fertilizerId);
    if (optionalFertilizer.isEmpty()) {
      throw new NotFoundException("Fertilizante não encontrado!");
    }

    Crop crop = optionalCrop.get();
    Fertilizer fertilizer = optionalFertilizer.get();

    crop.getFertilizers().add(fertilizer);
    farmService.setCrop(crop);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Endpoint para obter os fertilizantes associados a uma plantação pelo ID da plantação.
   *
   * @param cropId O ID da plantação
   * @return Um ResponseEntity contendo a lista de fertilizantes associados à plantaçã.,
   * @throws NotFoundException Se a plantação não for encontrada
   */

  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<Set<Fertilizer>> associateCropWithFertilizer(@PathVariable Long cropId) {
    Optional<Crop> optionalCrop = farmService.getCropById(cropId);
    if (optionalCrop.isEmpty()) {
      throw new NotFoundException("Plantação não encontrada!");
    }

    Crop crop = optionalCrop.get();
    Set<Fertilizer> fertilizers = crop.getFertilizers();

    return ResponseEntity.ok(fertilizers);
  }
}
