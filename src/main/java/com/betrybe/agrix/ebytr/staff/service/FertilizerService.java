package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.models.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.models.repository.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para gerenciar entidades de Fertilizantes.
 */
@Service
public class FertilizerService {

  FertilizerRepository fertilizerRepository;

  /**
   * Construtor para FertilizerService.
   *
   * @param fertilizerRepository o repositório para entidades de Fertilizantes
   */

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer create(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAllFertilizer() {
    return fertilizerRepository.findAll();
  }

  public Optional<Fertilizer> findByIdFertilizer(Long id) {
    return fertilizerRepository.findById(id);
  }
}
