package com.trabalhodevweb.crm.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuario").hasRole("ORG")
                        .requestMatchers(HttpMethod.GET, "/usuario/{id}").hasRole("ORG")
                        .requestMatchers(HttpMethod.PUT, "/usuario/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuario/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/usuario/{id}/cargo").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/usuario").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/recurso").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/recurso").hasRole("ORG")
                        .requestMatchers(HttpMethod.GET, "/recurso").hasRole("ORG")
                        .requestMatchers(HttpMethod.PUT, "/recurso").hasRole("ORG")
                        .requestMatchers(HttpMethod.POST, "/espaco").hasRole("ORG")
                        .requestMatchers(HttpMethod.GET, "/espaco").hasRole("ORG")
                        .requestMatchers(HttpMethod.PUT, "/espaco").hasRole("ORG")
                        .requestMatchers(HttpMethod.DELETE, "/espaco").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/evento").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/evento").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/evento").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/evento").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/evento/{id}/edicao").hasRole("ORG")
                        .requestMatchers(HttpMethod.GET, "/edicao").hasRole("ORG")
                        .requestMatchers(HttpMethod.PUT, "/edicao").hasRole("ORG")
                        .requestMatchers(HttpMethod.DELETE, "/edicao").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/atividade").hasRole("ORG")
                        .requestMatchers(HttpMethod.GET, "/atividade").hasRole("ORG")
                        .requestMatchers(HttpMethod.PUT, "/atividade").hasRole("ORG")
                        .requestMatchers(HttpMethod.DELETE, "/atividade").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
