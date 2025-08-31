package com.TradeShift.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;

import com.TradeShift.Jwt.JwtAuthenticationFilter;
import com.TradeShift.Service.JpaUserDetailsService;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	public SecurityConfig(JwtAuthenticationFilter jwtFilter, JpaUserDetailsService uds) {
	 this.jwtFilter = jwtFilter;
	 this.userDetailsService = uds;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	 http
	   .csrf(csrf -> csrf.disable())
	   .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	   .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	   .authorizeHttpRequests(auth -> auth
	       .requestMatchers("/api/auth/**", "/h2-console/**").permitAll()
	       .requestMatchers("/api/role/**", "/h2-console/**").permitAll()
	       .anyRequest().authenticated()
	   )
	   .headers(h -> h.frameOptions(f -> f.disable())) // H2 console
	   .authenticationProvider(daoAuthenticationProvider())
	   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	
	 return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	 return config.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
	 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	 provider.setUserDetailsService(userDetailsService);
	 provider.setPasswordEncoder(passwordEncoder());
	 return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	 return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	 CorsConfiguration config = new CorsConfiguration();
	 config.setAllowedOrigins(List.of("http://localhost:5173")); // React dev
	 config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
	 config.setAllowedHeaders(List.of("Authorization","Content-Type"));
	 config.setAllowCredentials(true);
	 UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	 source.registerCorsConfiguration("/**", config);
	 return source;
	}
}
