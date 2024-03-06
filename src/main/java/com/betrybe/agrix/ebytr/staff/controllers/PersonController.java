package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.controllers.dto.PersonResponseDto;
import com.betrybe.agrix.ebytr.staff.models.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para operações relacionadas a pessoas. Este controlador fornece endpoints para criar
 * e recuperar informações sobre pessoas.
 */

@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  /**
   * Construtor para o controlador de pessoas.
   *
   * @param personService O serviço responsável por operações relacionadas a pessoas.
   */

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Endpoint para criar uma nova pessoa.
   *
   * @param personDto Os dados da pessoa a serem criados.
   * @return Um ResponseEntity contendo os dados da pessoa criada.
   */

  @PostMapping()
  public ResponseEntity<PersonResponseDto> create(@RequestBody PersonDto personDto) {
    Person person = personService.create(personDto.toPerson());
    PersonResponseDto personResponseDto = new PersonResponseDto(person.getId(),
        person.getUsername(), person.getRole());
    return ResponseEntity.status(HttpStatus.CREATED).body(personResponseDto);
  }
}
