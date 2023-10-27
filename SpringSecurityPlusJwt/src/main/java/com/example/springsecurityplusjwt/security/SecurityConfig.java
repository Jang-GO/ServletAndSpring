package com.example.springsecurityplusjwt.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/*");
    }

    @Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/로그인페이지", "/css/**", "/images/**", "/js/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/로그인페이지")
                .loginProcessingUrl("/실제 로그인이 되는 url")
                .permitAll()

        http
                .sessionManagement()
                .invalidSessionUrl("/로그인페이지")

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/실제 로그아웃이 되는 url"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();


        //CSRF 토큰
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        return http.build();
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
