package com.betrybe.agrix.ebytr.staff.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Um serviço para geração e validação de tokens JWT (JSON Web Tokens).
 */
@Service
public class TokenService {

  private final Algorithm algorithm;

  /**
   * Construtor da classe TokenService.
   *
   * @param secret A chave secreta usada para assinar e verificar os tokens JWT.
   */
  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Gera um token JWT para o usuário especificado.
   *
   * @param username O nome de usuário para o qual o token será gerado.
   * @return O token JWT gerado.
   */
  public String generateToken(String username) {
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(generateExpiration())
        .sign(algorithm);
  }

  /**
   * Gera o tempo de expiração para o token JWT, configurado para 2 horas a partir do momento atual.
   *
   * @return O instante representando o tempo de expiração do token.
   */
  private Instant generateExpiration() {
    return Instant.now()
        .plus(2, ChronoUnit.HOURS);
  }

  /**
   * Valida um token JWT e retorna o nome de usuário contido nele.
   *
   * @param token O token JWT a ser validado.
   * @return O nome de usuário contido no token JWT, se válido.
   */
  public String validateToken(String token) {
    return JWT.require(algorithm)
        .build()
        .verify(token)
        .getSubject();
  }
}
