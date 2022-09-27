package com.example.CheckpointBackEndIEquipe08.security;

import com.example.CheckpointBackEndIEquipe08.repository.IUserRepository;
import com.example.CheckpointBackEndIEquipe08.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .antMatchers(HttpMethod.POST,"/endereco/registrar","/paciente/registrar").hasAnyRole("PACIENTE", "ADMIN")
                .antMatchers(HttpMethod.POST,"/dentista/registrar").hasAnyRole("DENTISTA", "ADMIN")
                .antMatchers(HttpMethod.GET,"/endereco/{id}","/consulta/{id}", "/paciente/{id}").hasAnyRole("PACIENTE", "ADMIN")
                .antMatchers(HttpMethod.GET,"/dentista/{id}","/consulta/{id}").hasAnyRole("DENTISTA", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/endereco/{id}","/paciente/{id}").hasAnyRole("PACIENTE", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/dentista/{id}").hasAnyRole("DENTISTA", "ADMIN")
                .antMatchers(HttpMethod.POST,"/consulta/cadastrar").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/consulta/{id}", "/consulta/buscar").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/consulta/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/consulta/{id}","/endereco/{id}","/paciente/{id}").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
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

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
