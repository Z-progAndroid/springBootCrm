package com.inmozara.crm.config.security;

import com.inmozara.crm.config.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authRequest -> authRequest.requestMatchers(
                                "/api_v1/crm/auth/**",
                                "/api_v1/crm/inmueble/all",
                                "/api_v1/crm/inmueble/findAllRelaciones",
                                "/api_v1/crm/inmueble/search",
                                "/api_v1/crm/inmueble/searchSinRelaciones",
                                "/api_v1/crm/inmueble/download-pdf",
                                "/api_v1/crm/inmueble/findAllDisponibles",
                                "/api_v1/crm/inmueble",
                                "/api_v1/crm/estado-contrato/all",
                                "/api_v1/crm/tipo-contrato/all",
                                "/api_v1/crm/tipo-pago/all",
                                "/api_v1/crm/barrio/all",
                                "/api_v1/crm/barrio/findAllByMunicipio",
                                "/api_v1/crm/estadoInmueble/all",
                                "/api_v1/crm/municipio/all",
                                "/api_v1/crm/municipio/municipiosByProvincia",
                                "/api_v1/crm/pais/all",
                                "/api_v1/crm/provincia/provinciasByPais",
                                "/api_v1/crm/provincia/all",
                                "/api_v1/crm/tipoInmueble/all",
                                "/api_v1/crm/estado_tarea/all",
                                "/api_v1/crm/usuario-estado/all"
                        ).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

}