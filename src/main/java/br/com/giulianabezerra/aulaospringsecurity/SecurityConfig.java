package br.com.giulianabezerra.aulaospringsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(
            authorizeConfig -> {
              authorizeConfig.requestMatchers("/public").permitAll();
              authorizeConfig.requestMatchers("/logout").permitAll();
              authorizeConfig.anyRequest().authenticated();
            })
        .oauth2Login(Customizer.withDefaults())
        .oauth2ResourceServer(config -> {
          config.jwt(Customizer.withDefaults());
        })
        .build();
  }

}
