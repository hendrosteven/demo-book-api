package com.brainmatics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.brainmatics.services.UserService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserService userService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new CorsConfig(), ChannelProcessingFilter.class);
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/actuator/**").permitAll()
			.antMatchers("/v2/api-docs/**").permitAll()
			.antMatchers("/configuration/ui/**").permitAll()
			.antMatchers("/swagger-resources/**").permitAll()
			.antMatchers("/configuration/security/**").permitAll()
			.antMatchers("/swagger-ui.html/**").permitAll()
			.antMatchers("/webjars/**").permitAll()
			.antMatchers(HttpMethod.POST, "/user").permitAll()
			.antMatchers(HttpMethod.POST, "/user/login").permitAll()
			.antMatchers(HttpMethod.GET, "/books/**").permitAll()
			.antMatchers(HttpMethod.GET, "/categories/**").permitAll()
			.anyRequest().fullyAuthenticated()
			.and().httpBasic()
			.and().csrf().disable();
	}
	
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/v2/api-docs",
	                "/configuration/ui",
	                "/swagger-resources",
	                "/configuration/security",
	                "/swagger-ui.html",
	                "/webjars/**");
	    }

//	

   @SuppressWarnings("deprecation")
   @Bean
   public static NoOpPasswordEncoder passwordEncoder() {
       return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
   }
   

}
