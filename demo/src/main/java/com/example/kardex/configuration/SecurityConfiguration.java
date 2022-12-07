package com.example.kardex.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {

	private final UserDetailsService userDetailsService;
	private final JWTAutorizationFilter jwtAutorizationFilter;
	
	
	public SecurityConfiguration (UserDetailsService userDetailsService,
			JWTAutorizationFilter jwtAutorizationFilter) {
		this.userDetailsService = userDetailsService;
		this.jwtAutorizationFilter = jwtAutorizationFilter;
	}
	
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/registrarUsuario", "/h2-console/**");
    }
    
    
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{
    	
    	JWTAutenticationFilter jwtAutenticationFilter = new JWTAutenticationFilter();
    	jwtAutenticationFilter.setAuthenticationManager(authManager);
    	jwtAutenticationFilter.setFilterProcessesUrl("/login");
    	
    	return http
    			.csrf().disable()
    			.authorizeHttpRequests()
    			.anyRequest()
    			.authenticated()
    			.and()
    			.headers()
    			.frameOptions().disable()
    			.and()
    		    //.httpBasic()
    	    	//		.and()
    			.sessionManagement()
    			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    			.and()
    			.addFilter(jwtAutenticationFilter)
    			.addFilterBefore(jwtAutorizationFilter, UsernamePasswordAuthenticationFilter.class)
    			.build();
    }
    
    
    
    
//    @Bean
//    public UserDetailsManager userDetailsManager() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(org.springframework.security.core.userdetails.User.withUsername("admin")
//        		.password(passwordEncoder().encode("admin"))
//        		.roles()
//        		.build());
//        return manager;
//        		
//    }
    
    @Bean
    AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
    	return http.getSharedObject(AuthenticationManagerBuilder.class)
    			.userDetailsService(userDetailsService)
    			.passwordEncoder(passwordEncoder())
    			.and()
    			.build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
        
    
    
    

}
