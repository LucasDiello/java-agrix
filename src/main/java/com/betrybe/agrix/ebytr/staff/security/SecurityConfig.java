package com.betrybe.agrix.ebytr.staff.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração de segurança para a aplicação.
 * Esta classe configura as políticas de segurança, como desativação do CSRF
 * e a configuração da política de gerenciamento de sessão como STATELESS (sem estado).
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * Configuração do filtro de segurança para a aplicação.
   *
   * @param httpSecurity O objeto HttpSecurity usado para configurar as políticas de segurança.
   * @return Um filtro de segurança configurado.
   * @throws Exception Se ocorrer um erro ao configurar o filtro de segurança.
   */

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .build();
  }
}