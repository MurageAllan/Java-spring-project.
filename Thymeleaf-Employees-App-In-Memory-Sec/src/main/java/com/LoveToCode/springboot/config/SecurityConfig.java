package com.LoveToCode.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails theUser = User.withUsername("Allan").password("{noop}allan").roles("EMPLOYEE").build();
		UserDetails theManager = User.withUsername("James").password("{noop}james").roles("EMPLOYEE", "MANAGER")
				.build();
		UserDetails theAdmin = User.withUsername("Ivy").password("{noop}ivy").roles("EMPLOYEE", "ADMIN").build();
		return new InMemoryUserDetailsManager(theUser, theManager, theAdmin);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http
				.authorizeRequests(configurer -> configurer.antMatchers("/employees/showForm*")
						.hasAnyRole("MANAGER", "ADMIN").antMatchers("/employees/save*").hasAnyRole("MANAGER", "ADMIN")
						.antMatchers("/employees/delete*").hasRole("ADMIN").antMatchers("/employees/**")
						.hasRole("EMPLOYEE").antMatchers("/resources/**").permitAll())
				.formLogin(form -> form.loginPage("/showLogin").loginProcessingUrl("/employees/login").permitAll())

				.logout(configurer -> configurer.permitAll())

				.exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"))

				.build();
	}
}
