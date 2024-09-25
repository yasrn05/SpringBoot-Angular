package com.project.backend.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.backend.filters.JwtTokenFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests.requestMatchers(
                            String.format("%s/users/register", apiPrefix),
                            String.format("%s/users/login", apiPrefix))
                            .permitAll()
                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/orders/**", apiPrefix))
                            .hasAnyRole("USER", "ADMIN")
                            .requestMatchers(HttpMethod.POST,
                                    String.format("%s/orders/**", apiPrefix))
                            .hasRole("USER")
                            .requestMatchers(HttpMethod.PUT,
                                    String.format("%s/orders/**", apiPrefix))
                            .hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE,
                                    String.format("%s/orders/**", apiPrefix))
                            .hasRole("ADMIN")
                            .anyRequest().authenticated();
                });
        return httpSecurity.build();
    };
}
