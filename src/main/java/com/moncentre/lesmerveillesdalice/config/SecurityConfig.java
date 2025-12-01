package com.moncentre.lesmerveillesdalice.config;

import com.moncentre.lesmerveillesdalice.security.JwtAuthenticationFilter;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                // ⭐ CORS doit être ici et une seule fois
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // 🔓 Login / Register accessibles
                        .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()

                        // 🔓 Rendez-vous (client)
                        .requestMatchers("/api/rendezvous/**").permitAll()

                        // 🔓 Dossier client automatique (création depuis RDV)
                        .requestMatchers(HttpMethod.POST, "/api/dossiers/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/dossiers/**").permitAll()

                        // 🔓 Client form RDV
                        .requestMatchers("/api/client-rdv/**").permitAll()

                        // 🔒 Utilisateurs (protégés)
                        .requestMatchers(HttpMethod.GET, "/api/utilisateurs/**")
                                .hasAnyRole("ADMIN", "PERSONNEL")
                        .requestMatchers(HttpMethod.POST, "/api/utilisateurs/**")
                                .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/utilisateurs/**")
                                .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/utilisateurs/**")
                                .hasRole("ADMIN")

                        .anyRequest().permitAll() // ⭐ TEMPORAIRE le temps du dev
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ⭐ CONFIG CORS PRINCIPALE
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        config.setAllowedOrigins(List.of(
                "http://localhost:4200",
                "http://127.0.0.1:4200"
        ));

        config.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        config.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "X-Requested-With"
        ));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}