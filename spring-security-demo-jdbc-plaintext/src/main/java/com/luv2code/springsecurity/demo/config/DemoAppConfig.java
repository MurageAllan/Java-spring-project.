package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc // provide support for annotation driven
@ComponentScan(basePackages = "com.luv2code.springsecurity.demo")
@PropertySource({"classpath:persistence-mysql.properties"})
public class DemoAppConfig {
	// set variable to hold properties.
	@Autowired
	private Environment env;

	// set up a logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// define a bean for a view resolver
	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();

		internalResourceViewResolver.setPrefix("/WEB-INF/view/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}

	@Bean
	public DataSource securityDataSource() {
		// create the connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		// set jdbc driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {

			// throw new exception
			throw new RuntimeException();
		}

		// log the connection properties
		myLogger.info("jdbc.url " + env.getProperty("jdbc.driver"));
		myLogger.info("jdbv.user " + env.getProperty("jdbc.user"));

		// set database connection properties.
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));

		// set connection pool properties.
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		return securityDataSource;
	}

	// Read env property and convert into int
	private Integer getIntProperty(String propertyName) {

		String propertyValue = env.getProperty(propertyName);
		
		// convert the string into integer
		Integer intPropertyValue = Integer.parseInt(propertyValue);

		return intPropertyValue;
	}
}
