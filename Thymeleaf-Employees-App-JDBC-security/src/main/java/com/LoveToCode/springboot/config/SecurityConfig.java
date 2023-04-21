package com.LoveToCode.springboot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	public SecurityConfig(@Qualifier("securityDataSource") DataSource securityDataSource) {
		super();
		this.securityDataSource = securityDataSource;
	}

	@Bean
	public UserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(securityDataSource);

		return users;
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
