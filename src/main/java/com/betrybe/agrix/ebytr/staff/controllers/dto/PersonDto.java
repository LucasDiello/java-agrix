package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.models.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Data transfer object (DTO) para representar informações de uma pessoa. Este DTO é usado para
 * transferir informações de e para a camada de controle.
 */

public record PersonDto(Long id, String username, String password, Role role) {


  public Person toPerson() {
    return new Person(id, username, password, role);
  }

}
