package hr.tvz.rentabike.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.formLogin()
		    .loginPage("/login")
		      .defaultSuccessUrl("/home", true)
		        .failureUrl("/login?error=true")
		          .and()
		           .logout()
		            .logoutSuccessUrl("/login?logout=true")
		             .and()
		             .rememberMe()
		              .and()
		               .authorizeRequests().antMatchers("/css/**")
		                .permitAll()
		                 .antMatchers("/login","/registration")
		                  .anonymous()
		                   .antMatchers("/**")
		                    .authenticated().anyRequest()
		                     .permitAll()
	                          .and()
	                            .csrf().disable();
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username,password, enabled from user where username=?")
				.authoritiesByUsernameQuery("select username, role from user_role where username =?");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
