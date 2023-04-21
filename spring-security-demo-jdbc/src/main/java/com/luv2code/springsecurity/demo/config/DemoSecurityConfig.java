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
		UserDetails theUser = User.withUsername("Reza").password("{noop}reza").roles("USER").build();
		UserDetails theManager = User.withUsername("frobese").password("{noop}frobese").roles("MANAGER").build();
		UserDetails theAdmin = User.withUsername("admin").password("{noop}admin").roles("ADMIN").build();
		return new InMemoryUserDetailsManager(theAdmin, theManager, theUser);

	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// any request to access any section of the app ought to be authenticated
		http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
				// show the custom form at the request mapping /login then proceed posting the
				// data at /authenticateLogin for processing
				// permit all allows everyone to see the logging page there is no need to be
				// logged in
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/authenticateLogin").permitAll())
				.logout().permitAll();

		return http.build();
	}
}
