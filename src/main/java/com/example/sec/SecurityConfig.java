package com.example.sec;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery("select email, password, active from utilisateur where email=?")
		.authoritiesByUsernameQuery("select email, role from utilisateur where email=?")
		.rolePrefix("ROLE_")
		;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/appelOffres").permitAll()
			.antMatchers("/cmtInscrire").permitAll()
			.antMatchers("/login_success").hasRole("AP")
			.antMatchers("/portailAdmin").hasRole("ADMIN") 
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.permitAll()
				.loginPage("/login")
				.usernameParameter("email")
				.defaultSuccessUrl("/login_success")
			.and()
			.logout()
				.permitAll()
				.logoutSuccessUrl("/appelOffres")
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			;
	}
	
	
}
