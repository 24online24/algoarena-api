package org.judy.algoarena.auth.config;

import org.judy.algoarena.auth.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(
                        (cors) -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.addAllowedOrigin("*");
                            config.addAllowedMethod("*");
                            config.addAllowedHeader("*");
                            cors.configurationSource(request -> config);
                        })
                .csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().permitAll()
                // THIS US IS FOR AUTH, PRODUCTION ONLY!
                // .requestMatchers("/auth/logout").authenticated()
                // .requestMatchers("/auth/**","landing").permitAll()
                // .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
