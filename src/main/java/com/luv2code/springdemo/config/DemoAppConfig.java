package com.luv2code.springdemo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.luv2code.springdemo")
@PropertySource({"classpath:persistence-mysql.properties", "classpath:security-persistence-mysql.properties"})
public class DemoAppConfig implements WebMvcConfigurer{
	
	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// define a bean for view resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	// define a bean for data source
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		// set the jdbc driver class
		// env.getProperty("jdbc.driver") reads information from .properties file
		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
			
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		logger.info("\n>>> url: " + env.getProperty("jdbc.url"));
		logger.info("\n>>> user name: " + env.getProperty("user"));
		
		// set database connection props
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool properties
		dataSource.setInitialPoolSize(toInteger("connection.pool.initialPoolSize"));
		dataSource.setMinPoolSize(toInteger("connection.pool.minPoolSize"));
		dataSource.setMaxPoolSize(toInteger("connection.pool.maxPoolSize"));
		dataSource.setMaxIdleTime(toInteger("connection.pool.maxIdleTime"));

		return dataSource;
	}
	
	// define a bean for security data source
	@Bean
	public DataSource securityDataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		// set the jdbc driver class
		try {
			dataSource.setDriverClass(env.getProperty("security.jdbc.driver"));
			
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		// to make sure that we are reading data from properties
		logger.info(">>> security url: " + env.getProperty("security.jdbc.url"));
		logger.info(">>> security user name: " + env.getProperty("security.jdbc.user"));
		
		// set database connection props
		dataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
		dataSource.setUser(env.getProperty("security.jdbc.user"));
		dataSource.setPassword(env.getProperty("security.jdbc.password"));
		
		// set connection pool properties
		dataSource.setInitialPoolSize(toInteger("security.connection.pool.initialPoolSize"));
		dataSource.setMinPoolSize(toInteger("security.connection.pool.minPoolSize"));
		dataSource.setMaxPoolSize(toInteger("security.connection.pool.maxPoolSize"));
		dataSource.setMaxIdleTime(toInteger("security.connection.pool.maxIdleTime"));
		
		return dataSource;
	}
	
	private Properties getHibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return props;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		// create hibernate session factorie based on datasource ad config properties
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}
	
	@Bean
	public LocalSessionFactoryBean securitySessionFactory() {
		// create hibernate session factorie based on datasource ad config properties
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(securityDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(
			@Qualifier("sessionFactory") SessionFactory sessionFactory
			) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		
		return transactionManager;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager securityTransactionManager(
			@Qualifier("securitySessionFactory") SessionFactory securitySessionFactory
			) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(securitySessionFactory);
		
		return transactionManager;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}

	private int toInteger(String property) {
		int intProp = Integer.parseInt(env.getProperty(property));
		return intProp;
	}
	

}
