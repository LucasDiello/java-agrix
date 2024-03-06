package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.models.entity.Crop;
import com.betrybe.agrix.ebytr.staff.models.entity.Farm;
import com.betrybe.agrix.ebytr.staff.models.repository.CropRepository;
import com.betrybe.agrix.ebytr.staff.models.repository.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class para lançar serviços da class.
 */

@Service
public class FarmService {

  private final CropRepository cropRepository;
  private final FarmRepository farmRepository;

  /**
   * Construtor da classe FarmService.
   *
   * @param farmRepository o repositório de fazendas
   * @param cropRepository o repositório de culturas
   */
  @Autowired
  public FarmService(FarmRepository farmRepository,
      CropRepository cropRepository) {

    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  public Optional<Farm> getFarmById(Long farmId) {
    return farmRepository.findById(farmId);
  }

  public Crop setCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  public Optional<Crop> getCropById(Long cropId) {
    return cropRepository.findById(cropId);
  }
}
