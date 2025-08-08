package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth

                        // Swagger : libre d'accès
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()

                        // Login : libre d'accès
                        .requestMatchers("/api/login").permitAll()

                        // Structures : GET publics
                        .requestMatchers(HttpMethod.GET,
                                "/api/structures",
                                "/api/structures/type/**",
                                "/api/structures/search",
                                "/api/structures/region/**",
                                "/api/structures/available_docs/**",
                                "/api/structures/filter"
                        ).permitAll()

                        .requestMatchers(HttpMethod.POST,
                                "/api/admins"
                        ).permitAll()

                        // Structures : POST /api/structures => ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/structures").permitAll()

                        // Structures : POST document => MEMBRE_STRUCTURE
                        .requestMatchers(HttpMethod.POST, "/api/structures/{id:[\\d]+}/document").permitAll()

                        // Structures : GET /api/structures/{id} => ADMIN
                        .requestMatchers(HttpMethod.GET, "/api/structures/{id:[\\d]+}").hasRole("ADMIN")

                        // Structures : PUT => ADMIN, MEMBRE_STRUCTURE
                        .requestMatchers(HttpMethod.PUT, "/api/structures/{id:[\\d]+}").hasAnyRole("ADMIN", "MEMBRE_STRUCTURE")

                        // Structures : DELETE => ADMIN
                        .requestMatchers(HttpMethod.DELETE, "/api/structures/{id:[\\d]+}").hasRole("ADMIN")

                        // MembresStructures : POST, GET, GET by id, DELETE => ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/membres_structures").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/membres_structures").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/membres_structures/{id:[\\d]+}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/membres_structures/{id:[\\d]+}").hasRole("ADMIN")

                        // MembresStructures : PUT => ADMIN, MEMBRE_STRUCTURE
                        .requestMatchers(HttpMethod.PUT, "/api/membres_structures/{id:[\\d]+}").hasAnyRole("ADMIN", "MEMBRE_STRUCTURE")
                )
                .httpBasic(Customizer.withDefaults())
                .anonymous(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

