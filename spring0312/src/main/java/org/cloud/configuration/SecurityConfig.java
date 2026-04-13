package org.cloud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf-> csrf.disable())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/css/**", "/js/**", "/images/**", "/favicon.ico")
				.permitAll().requestMatchers("/", "/member/join.do", "/member/login.do", "/member/insertMember.do", "/board/openBoardList.do")
				.permitAll().anyRequest().authenticated())
		.formLogin(login -> login.loginPage("/member/login.do")
				.loginProcessingUrl("/member/loginProc.do")
				.usernameParameter("userId")
				.passwordParameter("password")
				.defaultSuccessUrl("/board/openBoardList.do", true)
				.permitAll())
		.logout(logout -> logout
				.logoutUrl("/member/logout.do")
				.logoutSuccessUrl("/board/openBoardList.do")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID"));
		
		return http.build();
	}
}









