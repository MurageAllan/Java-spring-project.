package com.LoveToCode.springmvc.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan("com.LoveToCode.springmvc")
@PropertySource({"classpath:persistence-mysql.properties" , " classpath:security-persistence-mysql.properties"})
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env;

	private Logger myLogger = Logger.getLogger(getClass().getName());

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setSuffix(".jsp");
		viewResolver.setPrefix("/WEB-INF/view/");

		return viewResolver;
	}

	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			System.out.println(e.getMessage());
		}

		myLogger.info("My jdbc url is " + env.getProperty("jdbc.url"));
		myLogger.info("My user is " + env.getProperty("jdbc.user"));

		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));

		dataSource.setInitialPoolSize(getPropertyValue("connection.pool.initialPoolSize"));
		dataSource.setMinPoolSize(getPropertyValue("connection.pool.minPoolSize"));
		dataSource.setMaxPoolSize(getPropertyValue("connection.pool.maxPoolSize"));
		dataSource.setMaxIdleTime(getPropertyValue("connection.pool.maxIdleTime"));

		return dataSource;
	}

	@Bean
	public DataSource securityDataSource() {
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		try {
			securityDataSource.setDriverClass(env.getProperty("security.jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}

		myLogger.info("security.jdbc.url=" + env.getProperty("security.jdbc.url"));
		myLogger.info(">>> security.jdbc.user=" + env.getProperty("security.jdbc.user"));

		securityDataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
		securityDataSource.setUser(env.getProperty("security.jdbc.user"));
		securityDataSource.setPassword(env.getProperty("security.jdbc.password"));

		securityDataSource.setInitialPoolSize(getPropertyValue("security.connection.pool.initialPoolSize"));

		securityDataSource.setMinPoolSize(getPropertyValue("security.connection.pool.minPoolSize"));

		securityDataSource.setMaxPoolSize(getPropertyValue("security.connection.pool.maxPoolSize"));

		securityDataSource.setMaxIdleTime(getPropertyValue("security.connection.pool.maxIdleTime"));
		return securityDataSource;
	}

	public Integer getPropertyValue(String propName) {

		String propValue = env.getProperty(propName);

		Integer intPropValue = Integer.parseInt(propValue);

		return intPropValue;

	}

	private Properties getHibernateProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return properties;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		return sessionFactory;

	}

	@Bean
	public HibernateTransactionManager transanctionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transanctionManager = new HibernateTransactionManager();
		transanctionManager.setSessionFactory(sessionFactory);
		return transanctionManager;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
