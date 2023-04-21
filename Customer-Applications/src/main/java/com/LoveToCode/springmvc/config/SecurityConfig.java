package com.LoveToCode.springmvc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private DataSource securityDataSource;

	@Autowired
	public SecurityConfig(DataSource securityDataSource) {
		this.securityDataSource = securityDataSource;
	}

	@Bean
	public UserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(securityDataSource);
		return users;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Throwable {
		return http
				.authorizeHttpRequests(configurer -> configurer.antMatchers("/customer/showForm*")
						.hasAnyRole("MANAGER", "ADMIN").antMatchers("/customer/save*").hasAnyRole("MANAGER", "ADMIN")
						.antMatchers("/customer/delete").hasRole("ADMIN").antMatchers("/customer/**")
						.hasRole("EMPLOYEE").antMatchers("/resources/**").permitAll())

				.formLogin(form -> form.loginPage("/showLogin").loginProcessingUrl("/customer/login").permitAll())

				.logout(configurer -> configurer.permitAll())

				.exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied")).build();
	}
}
