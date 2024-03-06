package com.betrybe.agrix.ebytr.staff.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;

/**
 * Entidade que representa um fertilizante.
 */

@Entity
@Table(name = "fertilizer")
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String brand;
  private String composition;

  @JsonIgnore
  @ManyToMany(mappedBy = "fertilizers")
  private Set<Crop> crops;

  public Fertilizer() {}

  /**
   * Construtor da classe Fertilizer com parâmetros.
   *
   * @param name        o nome do fertilizante
   * @param brand       a marca do fertilizante
   * @param composition a composição do fertilizante
   */

  public Fertilizer(String name, String brand, String composition) {
    this.name = name;
    this.brand = brand;
    this.composition = composition;
  }

  public Set<Crop> getCrops() {
    return crops;
  }

  public void setCrops(Set<Crop> crops) {
    this.crops = crops;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getComposition() {
    return composition;
  }

  public void setComposition(String composition) {
    this.composition = composition;
  }
}
