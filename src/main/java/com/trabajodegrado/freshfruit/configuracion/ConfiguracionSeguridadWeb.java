package com.trabajodegrado.freshfruit.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.trabajodegrado.freshfruit.seguridad.JWTFiltroAutorizacion;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfiguracionSeguridadWeb extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors().and().csrf().disable().addFilterAfter(new JWTFiltroAutorizacion(), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, 
			"/usuarios/login"
				)
		.permitAll()
		.antMatchers(HttpMethod.GET,
			"/swagger-ui.html", 
			"/swagger-resources/**", 
			"/v2/api-docs", 
			"/webjars/**")
		.permitAll()
		.antMatchers(HttpMethod.PUT
		    ).permitAll()
		.anyRequest()
		.authenticated();
    }

}
