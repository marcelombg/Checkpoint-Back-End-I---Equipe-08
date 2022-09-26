package com.example.CheckpointBackEndIEquipe08.security;

import com.example.CheckpointBackEndIEquipe08.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/user","/user/**").permitAll()
                .antMatchers(HttpMethod.GET,"/endereco/registrar").hasAnyRole("ADMIN_ROLES", "PACIENTE_ROLES", "DENTISTA_ROLES")
                .antMatchers(HttpMethod.GET,"/paciente/registrar").hasAnyRole("ADMIN_ROLES", "PACIENTE_ROLES", "DENTISTA_ROLES")
                .antMatchers(HttpMethod.GET,"/dentista/registrar").hasAnyRole("ADMIN_ROLES", "PACIENTE_ROLES", "DENTISTA_ROLES")
                .antMatchers(HttpMethod.GET,"/consulta/cadastrar").hasAnyRole("ADMIN_ROLES", "PACIENTE_ROLES", "DENTISTA_ROLES")
                .antMatchers("/endereco").hasAnyRole("ADMIN_ROLES")
                .antMatchers("/paciente").hasAnyRole("ADMIN_ROLES")
                .antMatchers("/dentista").hasAnyRole("ADMIN_ROLES")
                .antMatchers("/consulta").hasAnyRole("ADMIN_ROLES")
                .anyRequest()
                .authenticated().and()
//                .formLogin();
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);

        return provider;
    }
}
