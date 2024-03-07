package com.betrybe.agrix.ebytr.staff.controllers.dto;

/**
 * DTO (Objeto de Transferência de Dados) para jwt.
 * Este registro representa os dados de autenticação contendo um Token jwt
 */

public record TokenDto(String token) {
}