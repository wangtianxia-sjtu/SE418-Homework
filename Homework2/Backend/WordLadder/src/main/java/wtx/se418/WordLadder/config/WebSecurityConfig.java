package wtx.se418.WordLadder.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

@Override
protected void configure(HttpSecurity http) throws Exception {
    http
    .cors().and()
    .csrf().disable()
	.authorizeRequests()
            .antMatchers("/login", "/success", "/failure", "/unauthorized","/logout").permitAll()
            .antMatchers("/actuator", "/actuator/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
    .formLogin()
        .loginPage("/unauthorized")
        .failureForwardUrl("/failure")
        .successForwardUrl("/success")
        .loginProcessingUrl("/login")
        .and()
    .logout()
        .logoutSuccessUrl("/success");
}

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
    .passwordEncoder(new BCryptPasswordEncoder())
    .withUser("user").password(new BCryptPasswordEncoder().encode("password")).roles("USER")
    .and()
    .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("ADMIN");
}


}