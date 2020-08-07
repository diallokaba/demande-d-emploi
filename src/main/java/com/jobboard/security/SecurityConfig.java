package com.jobboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email as principal, password as credentials, etat FROM user WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT email as principal, nom as role FROM user_roles WHERE email = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.formLogin();//pour afficher un formulaire de connexion par defaut
        http.formLogin().loginPage("/jobboard/login");//personnaliser le form de login
        //les droits dun USER
        http.authorizeRequests().antMatchers("/jobboard/presentation","/jobboard/entreprise").hasAnyAuthority("ROLE_ENTREPRISE");
        //les droits du role ETUDIANT
        http.authorizeRequests().antMatchers("/jobboard/presentation","/jobboard/etudiant").hasAnyAuthority("ROLE_DEMANDEUR_EMPLOI");
        http.authorizeRequests().antMatchers("/jobboard/presentation").hasAnyAuthority("ROLE_DEMANDEUR_EMPLOI","ROLE_DEMANDEUR_EMPLOI");
        //les droits du role PROFESSEUR
        //http.authorizeRequests().antMatchers("/online/professeurs").hasAuthority("ROLE_PROFESSEUR");
        //gestion des droits
        http.exceptionHandling().accessDeniedPage("/jobboard/403");
        http.csrf().disable();
    }
}
