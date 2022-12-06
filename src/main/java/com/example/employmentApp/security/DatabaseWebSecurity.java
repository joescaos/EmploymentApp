package com.example.employmentApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, status from users where username=?")
                .authoritiesByUsernameQuery("select u.username, p.profile from userProfile up " +
                        "inner join users u on u.id = up.idUser " +
                        "inner join profiles p on p.id = up.idProfile " +
                        "where u.username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(
                        "/bootstrap/**",
                        "/images/**",
                        "/tinymce/**",
                        "/logos/**"
                ).permitAll()
                .antMatchers(
                        "/",
                        "/signup",
                        "/search",
                        "/positions/view/**",
                        "/bcrypt/**"
                ).permitAll()
                .antMatchers("/positions/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
                .antMatchers("/categories/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
                .antMatchers("/users/**").hasAnyAuthority("ADMINISTRADOR")
                .anyRequest().authenticated()
                .and().formLogin().permitAll();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
