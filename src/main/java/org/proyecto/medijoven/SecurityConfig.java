package org.proyecto.medijoven;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) -> {
			auth.requestMatchers("/css/**", "/img/**", "/js/**").permitAll();
			auth.requestMatchers("/medijoven/dashboard/inicio").hasRole("ADMINISTRADOR");
			auth.anyRequest().authenticated();
		}).formLogin(form -> form
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/dashboard"))
		.logout((log) -> log
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.permitAll());

		return http.build();
	}
	
	
	/*
	@Bean
	UserDetailsService userDetailsService(BCryptPasswordEncoder encoder) {
		InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
		detailsManager.createUser(User
				.withUsername("gian")
				.password(encoder.encode("123"))
				.roles("user")
				.build());

		return detailsManager;
	}
	*/
	
	
}
