package org.proyecto.medijoven;

import org.proyecto.medijoven.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Bean
	UserDetailsService getUserDetailsService() {
		return new  CustomUserDetailsService();
	}

	@Bean
	BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return daoAuthenticationProvider;
	}
	
	
	
	protected void config(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(getAuthenticationProvider());
	}
	

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) -> {
			auth.requestMatchers("/css/**", "/img/**", "/js/**").permitAll();
			auth.requestMatchers("/cuenta/crear-cuenta").permitAll();
			auth.requestMatchers("/cuenta/registrar").permitAll();
			auth.requestMatchers("/medijoven/dashboard/inicio").hasRole("ADMINISTRADOR");
			auth.requestMatchers("/").permitAll();
			auth.anyRequest().authenticated();
		}).formLogin(form -> form
				.loginPage("/cuenta/login")
				.permitAll()
				.defaultSuccessUrl("/dashboard"))
		.logout((log) -> log
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.permitAll());

		return http.build();
	}
	
}
