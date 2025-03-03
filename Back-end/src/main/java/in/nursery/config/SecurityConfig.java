package in.nursery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.nursery.ServiceImpl.MyCustomerDetailsService;

// for configuring the security 
@Configuration	
public class SecurityConfig {

	@Autowired
	private MyCustomerDetailsService myCustomerDetailsService;
	
	@Autowired
	private AppFilter filter;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	
	 @Bean
	 public PasswordEncoder encoder() {
		 
		  return new BCryptPasswordEncoder();
		 
	 }
	 
	 @Bean
	 public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
		 
		return configuration.getAuthenticationManager();
	 }
	 
	 @Bean
	 public AuthenticationProvider authenticationProvider() {
		 
		  DaoAuthenticationProvider aDaoAuthenticationProvider = new DaoAuthenticationProvider();
		  aDaoAuthenticationProvider.setUserDetailsService(myCustomerDetailsService);
		  aDaoAuthenticationProvider.setPasswordEncoder(encoder());
		  
		  
		   
		  return aDaoAuthenticationProvider;
		 
	 }
		 
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
		return http.csrf()
	            .disable()
	            .authorizeHttpRequests()
	                .requestMatchers("/login", "checkAdminPassword", "/register","/createStaff","/contactUs","/staffLogin","/searchPlants")
	                    .permitAll()
	                .anyRequest()
	                    .authenticated()
	            .and()
	            .cors()
	            .and()
	            .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
	            .and()
	            .authenticationProvider(authenticationProvider())
	            .addFilterAt(filter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	
	 }
}
