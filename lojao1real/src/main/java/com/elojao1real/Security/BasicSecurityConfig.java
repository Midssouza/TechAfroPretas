package com.elojao1real.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().csrf().disable()
            .cors();

        http
    	        .authorizeHttpRequests((auth) -> auth
				.antMatchers("/**").permitAll()
				.antMatchers("/usuarios/logar").permitAll()
				.antMatchers("/usuarios/cadastrar").permitAll()
				.antMatchers(HttpMethod.GET ,"/usuarios/{id}").permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.anyRequest().authenticated())
		         .httpBasic();

        return http.build();

    }

}
