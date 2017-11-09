package com.sven.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        if (securityProperties.isRequireSsl()) {
            
            http.requiresChannel().anyRequest().requiresSecure();
        }
        
        http.authorizeRequests().antMatchers("/**").permitAll();
            
    }
}
