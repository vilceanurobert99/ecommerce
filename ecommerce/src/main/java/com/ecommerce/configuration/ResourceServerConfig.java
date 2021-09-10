package com.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Value("ecommerce")
    private String resourceId;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceId);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/register/").permitAll()
                .antMatchers(HttpMethod.POST, "/product/insert").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/users/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/users/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/product/delete").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/product/delete/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/category/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.OPTIONS, "**").authenticated()
                .anyRequest()
                .permitAll();
    }
}
