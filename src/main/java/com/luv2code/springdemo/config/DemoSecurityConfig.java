package com.luv2code.springdemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.luv2code.springdemo.service.UserService;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter{

	// reference to security data source
	@Autowired
	@Qualifier(value = "securityDataSource")
	private DataSource securityDataSource;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// add permits to access login, logout and registration
		http.authorizeHttpRequests()
					.requestMatchers("/register/**").permitAll()
					.requestMatchers("/customer/showForm*").hasAnyRole("MANAGER", "ADMIN")
					.requestMatchers("/customer/save*").hasAnyRole("MANAGER", "ADMIN")
					.requestMatchers("/customer/delete").hasAnyRole("ADMIN")
				.and()
					.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/authenticateUser")
					.permitAll()
				.and()
					.logout()
					.permitAll()
				.and()
					.exceptionHandling()
					.accessDeniedPage("/access-denied");
	}


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
}
