package com.project.backend.configurations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.backend.filters.JwtTokenFilter;
import com.project.backend.models.Role;

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
                                                        String.format("%s/users/login", apiPrefix),
                                                        String.format("%s/roles", apiPrefix))
                                                        .permitAll()
                                                        // .requestMatchers(HttpMethod.GET,
                                                        // String.format("%s/roles**", apiPrefix))
                                                        // .hasAnyRole(Role.USER, Role.ADMIN)
                                                        // Categories
                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/categories**", apiPrefix))
                                                        .hasAnyRole(Role.USER, Role.ADMIN)
                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/categories/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)
                                                        .requestMatchers(HttpMethod.PUT,
                                                                        String.format("%s/categories/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)
                                                        .requestMatchers(HttpMethod.DELETE,
                                                                        String.format("%s/categories/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)
                                                        // Products
                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/products**", apiPrefix))
                                                        .hasAnyRole(Role.USER, Role.ADMIN)
                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/products/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)
                                                        .requestMatchers(HttpMethod.PUT,
                                                                        String.format("%s/products/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)
                                                        .requestMatchers(HttpMethod.DELETE,
                                                                        String.format("%s/products/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)
                                                        // Orders
                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/orders/**", apiPrefix))
                                                        .hasAnyRole(Role.USER, Role.ADMIN)
                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/orders/**", apiPrefix))
                                                        .hasRole(Role.USER)
                                                        .requestMatchers(HttpMethod.PUT,
                                                                        String.format("%s/orders/**", apiPrefix))
                                                        .hasRole(Role.ADMIN)
                                                        .requestMatchers(HttpMethod.DELETE,
                                                                        String.format("%s/orders/**", apiPrefix))
                                                        .hasRole(Role.ADMIN)
                                                        // OrderDetail
                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/order_details/**", apiPrefix))
                                                        .hasAnyRole(Role.USER, Role.ADMIN)
                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/order_details/**", apiPrefix))
                                                        .hasRole(Role.USER)
                                                        .requestMatchers(HttpMethod.PUT,
                                                                        String.format("%s/order_details/**", apiPrefix))
                                                        .hasRole(Role.ADMIN)
                                                        .requestMatchers(HttpMethod.DELETE,
                                                                        String.format("%s/order_details/**", apiPrefix))
                                                        .hasRole(Role.ADMIN)
                                                        .anyRequest().authenticated();
                                })
                                .csrf(AbstractHttpConfigurer::disable);
                httpSecurity.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
                        @Override
                        public void customize(CorsConfigurer<HttpSecurity> httpSercurityCorsConfigurer) {
                                CorsConfiguration configuration = new CorsConfiguration();
                                configuration.setAllowedOrigins(List.of("*"));
                                configuration.setAllowedMethods(
                                                Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                                configuration.setAllowedHeaders(
                                                Arrays.asList("authorization", "content-type", "x-auth-token"));
                                configuration.setExposedHeaders(List.of("x-auth-token"));
                                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                                source.registerCorsConfiguration("/**", configuration);
                                httpSercurityCorsConfigurer.configurationSource(source);
                        }
                });
                return httpSecurity.build();
        };
}
