package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.models.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Data transfer object (DTO) para representar a resposta de uma pessoa.
 * Este DTO contém informações
 * como ID, nome de usuário e papel (role) de uma pessoa.
 */

public record PersonResponseDto(
    Long id,
    String username,
    Role role
) {

  /**
   * Cria um objeto PersonResponseDto a partir de uma entidade Person.
   *
   * @param person A entidade Person da qual extrair informações para criar o DTO.
   * @return Um objeto PersonResponseDto contendo informações da entidade Person fornecida.
   */

  public PersonResponseDto fromPerson(Person person) {
    return new PersonResponseDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }
}
