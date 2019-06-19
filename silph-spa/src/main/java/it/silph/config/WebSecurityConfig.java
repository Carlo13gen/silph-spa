package it.silph.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    
    @Autowired
    private Environment environment;

    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/", "/home").permitAll()
            .antMatchers(HttpMethod.GET, "/operazioni").hasAnyAuthority("DIPENDENTE")
          //.anyRequest().authenticated()
            .and()
            .formLogin()
            	.defaultSuccessUrl("/operazioni")
            	.loginPage("/login")
            	.failureUrl("/loginerror")
            .and()
             	.logout()
             		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             		.logoutSuccessUrl("/");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
        	.dataSource(this.buildDatasource())
            .authoritiesByUsernameQuery("SELECT username, role FROM dipendente WHERE username=?")
            .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM dipendente WHERE username=?");
    }

    @Bean
    DataSource buildDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SpringSecurityDialect springSecurityDialect() {
    	return new SpringSecurityDialect();
    }
}

