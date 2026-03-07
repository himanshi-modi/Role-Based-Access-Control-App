package com.project.demo.Security;

import com.project.demo.Entity.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JWTAuthFilter JWTAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception{
        http.csrf(csrf ->csrf.disable() )
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/auth/**", "/swagger-ui/**",
                                "/v3/api-docs/**","/public").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(JWTAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
