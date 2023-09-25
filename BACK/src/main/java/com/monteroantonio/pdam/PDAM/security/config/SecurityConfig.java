package com.monteroantonio.pdam.PDAM.security.config;

import com.monteroantonio.pdam.PDAM.security.error.JwtAccessDeniedHandler;
import com.monteroantonio.pdam.PDAM.security.error.JwtAuthenticationEntryPoint;
import com.monteroantonio.pdam.PDAM.security.jwt.access.JwtAuthenticationFilter;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)

@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/h2-console/**", "/auth/register", "/auth/login"));
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        AuthenticationManager authenticationManager =
                authenticationManagerBuilder.authenticationProvider(authenticationProvider())
                        .build();

        return authenticationManager;
    }



    /*
    @Bean
    public void authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        AuthenticationManager authenticationManager =
                authenticationManagerBuilder.authenticationProvider(authenticationProvider())
                        .build();

    }

     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setHideUserNotFoundExceptions(false);

        return authenticationProvider;

    }

    @Bean
    public SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll();
                })
                .csrf(csrf -> {
                    csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"));
                })
                .headers(headers -> {
                    headers.disable();
                })
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .exceptionHandling((exception)->
                        exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedPage("/error/access-denied")
                                .accessDeniedHandler(jwtAccessDeniedHandler))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/me/**").authenticated()
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/auth/register/admin").hasRole("ADMIN")
                        .anyRequest().permitAll()
                );


        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers((headers) ->
                headers
                        .frameOptions((frameOptions) -> frameOptions.disable())
        );

        return http.build();
    }

    /*

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .exceptionHandling((exception)->
                        exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/auth/register/admin")).hasRole("ADMIN")
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/me/**")).authenticated()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/user/**")).authenticated()
                        .anyRequest().permitAll())
                .build();




        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers((headers) ->
                headers
                        .frameOptions((frameOptions) -> frameOptions.disable())
        );

        return http.getOrBuild();
    }

     */

}
