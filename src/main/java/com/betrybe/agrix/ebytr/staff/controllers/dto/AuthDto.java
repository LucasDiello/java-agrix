package com.betrybe.agrix.ebytr.staff.controllers.dto;

/**
 * DTO (Objeto de Transferência de Dados) para autenticação.
 * Este registro representa os dados de autenticação contendo um nome de usuário e uma senha.
 */

public record AuthDto(String username, String password){
}