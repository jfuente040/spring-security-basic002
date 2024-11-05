package com.jfuente040.spring_security_basic002.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Endpoint level authorization

    // --- Matchers ---
    // 1. AnyRequest matcher - anyRequest() - matches any request
    // 2. RequestMatchers matcher - requestMatchers() - matches the given request
    // 3. RequestMatchers with HttpMethod - requestMatchers(HttpMethod) - matches the given request with the given HTTP method

    // --- Authorization rules ---
    // 1. PermitAll - permitAll() - allows access to any user
    // 2. DenyAll - denyAll() - denies access to any user
    // 3. Authenticated - authenticated() - allows access to authenticated users
    // 4. HasRole - hasRole() - allows access to users with the given role
    // 4.1 HasAnyRole - hasAnyRole() - allows access to users with any of the given roles
    // 5. HasAuthority - hasAuthority() - allows access to users with the given authority
    // 5.1 HasAnyAuthority - hasAnyAuthority() - allows access to users with any of the given authorities
    // 6. Access - access() - allows access to users with the given SpEL expression
    // Example: .access(new WebExpressionAuthorizationManager("hasRole('ROLE_ADMIN') or hasAuthority('ROLE_ADMIN')"))

    // Note: use .authorizeHttpRequests() to configure authorization rules and not .authorizeRequests()
    // because the latter is deprecated.


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequests ->
                        authRequests
                                //.requestMatchers("/api/demoget").hasRole("USER")
                                //.requestMatchers("/api/adminget").hasRole("ADMIN")
                                //.requestMatchers("/api/adminget").hasAuthority("ROLE_ADMIN")
                                //.requestMatchers("/api/adminget").access(new WebExpressionAuthorizationManager(
                                  //      "hasRole('USER') or hasRole('ADMIN') "))
                                //.requestMatchers("/api/adminget").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/add").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/add").authenticated()
                                .anyRequest().permitAll()
                )
                //.formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new InMemoryUserDetailsManager(
        User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .authorities("read")
                .roles("USER")
                .build(),
        User.withUsername("admin")
                .password(passwordEncoder.encode("password"))
                //.roles("USER", "ADMIN") // roles() is an alias for authorities() with the ROLE_ prefix
                .authorities("read", "write", "ROLE_USER", "ROLE_ADMIN")
                .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
