package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.exception.advice.NotFoundException;
import com.betrybe.agrix.ebytr.staff.models.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para operações relacionadas a fertilizantes.
 */

@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {

  FertilizerService fertilizerService;

  /**
   * Construtor para FertilizerController.
   *
   * @param fertilizerService o serviço de Fertilizantes
   */

  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping()
  public ResponseEntity<Fertilizer> create(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer fertilizer = fertilizerService.create(fertilizerDto.toFertilizer());
    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizer);
  }

  @GetMapping()
  public ResponseEntity<List<Fertilizer>> getAllFertilizer() {
    List<Fertilizer> fertilizer = fertilizerService.getAllFertilizer();
    return ResponseEntity.ok().body(fertilizer);
  }

  /**
   * Busca um fertilizante pelo ID.
   *
   * @param fertilizerId o ID do fertilizante a ser buscado
   * @return a resposta HTTP com o fertilizante encontrado, se existir
   * @throws NotFoundException se o fertilizante não for encontrado
   */

  @GetMapping("/{fertilizerId}")
  public ResponseEntity<Optional<Fertilizer>> findByIdFertilizer(@PathVariable Long fertilizerId) {
    Optional<Fertilizer> fertilizer = fertilizerService.findByIdFertilizer(fertilizerId);

    if (fertilizer.isEmpty()) {
      throw new NotFoundException("Fertilizante não encontrado!");
    }
    return ResponseEntity.ok().body(fertilizer);
  }
}
