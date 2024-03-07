package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controllers.dto.FarmDto;
import com.betrybe.agrix.ebytr.staff.exception.advice.NotFoundException;
import com.betrybe.agrix.ebytr.staff.models.entity.Crop;
import com.betrybe.agrix.ebytr.staff.models.entity.Farm;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para manipulação de fazendas.
 */
@RestController
@RequestMapping(value = "farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Constrói o controlador FarmController com o serviço FarmService.
   *
   * @param farmService o serviço FarmService
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Cria uma nova fazenda.
   *
   * @param farm os dados da fazenda a ser criada
   * @return a resposta HTTP com a fazenda criada
   */

  @PostMapping()
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farm) {
    Farm newFarm = farmService.insertFarm(farm.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  @GetMapping()
  @Secured({"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
  public List<Farm> getAllFarms() {
    return farmService.getAllFarms();
  }

  /**
   * Obtém uma fazenda pelo seu ID.
   *
   * @param farmId o ID da fazenda a ser obtida
   * @throws NotFoundException se a fazenda não for encontrada
   */

  @GetMapping("/{farmId}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long farmId) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }
    Farm farm = optionalFarm.get();
    return ResponseEntity.ok(farm);
  }

  /**
   * Define uma nova plantação em uma fazenda.
   *
   * @param farmId  o ID da fazenda onde a plantação será definida
   * @param cropDto os dados da plantação a ser definida
   * @return a resposta HTTP com a plantação definida
   * @throws NotFoundException se a fazenda não for encontrada
   */

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> setCrop(@PathVariable Long farmId, @RequestBody CropDto cropDto) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }

    Farm farm = optionalFarm.get();
    Crop newCrop = cropDto.toCrop();
    newCrop.setFarm(farm);
    Crop crop = farmService.setCrop(newCrop);

    CropDto responseCropDto = new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        farmId
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(responseCropDto);
  }

  /**
   * Obtém todas as plantações de uma fazenda pelo seu ID.
   *
   * @param farmId o ID da fazenda
   * @return a resposta HTTP com a lista de plantações da fazenda
   * @throws NotFoundException se a fazenda não for encontrada
   */

  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getCrops(@PathVariable Long farmId) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);
    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }

    Farm farm = optionalFarm.get();
    List<Crop> allCrops = farm.getCrops();
    List<CropDto> responseCropDto = allCrops.stream()
        .map(crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            farmId
        ))
        .toList();
    return ResponseEntity.ok(responseCropDto);
  }


}
