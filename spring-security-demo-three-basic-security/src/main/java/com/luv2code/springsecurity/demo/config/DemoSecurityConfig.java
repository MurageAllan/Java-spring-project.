package com.luv2code.springsecurity.demo.config;

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
public class DemoSecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails theUser = User.withUsername("allan").password("{noop}allan").roles("USER").build();
		UserDetails theManager = User.withUsername("frobese").password("{noop}frobese").roles("MANAGER").build();
		UserDetails theAdmin = User.withUsername("admin").password("{noop}admin").roles("ADMIN").build();
		return new InMemoryUserDetailsManager(theAdmin, theManager, theUser);

	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()) // any request made by the user should be authenticated
				.formLogin() // allows users to be authenticated by form based login.
				.and()
				.httpBasic(); // allows users to be authenticated with basic http authentication.
		return http.build();
	}
}
