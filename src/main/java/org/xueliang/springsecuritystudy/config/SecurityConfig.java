package org.xueliang.springsecuritystudy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.xueliang.springsecuritystudy.security.RestAccessDeniedHandler;
import org.xueliang.springsecuritystudy.security.RestAuthenticationEntryPoint;
import org.xueliang.springsecuritystudy.security.RestAuthenticationFailureHandler;
import org.xueliang.springsecuritystudy.security.RestAuthenticationSuccessHandler;
import org.xueliang.springsecuritystudy.security.RestLogoutSuccessHandler;
import org.xueliang.springsecuritystudy.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${api.csrftoken}")
    private String csrfTokenApi;
    
    @Value("${api.login}")
    private String loginApi;
    
    @Value("${api.logout}")
    private String logoutApi;
    
    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(csrfTokenApi).permitAll()
        .antMatchers("/api/user/**").access("hasAuthority('USER')")
        .antMatchers("/api/admin/**").access("hasAuthority('ADMIN')")
        .antMatchers("/api/dba/**").access("hasAuthority('DBA')")
        .antMatchers("/api/**").fullyAuthenticated()
        .and().formLogin().loginProcessingUrl(loginApi)
        .successHandler(new RestAuthenticationSuccessHandler())
        .failureHandler(new RestAuthenticationFailureHandler())
        .and().logout().logoutUrl(logoutApi)
        .logoutSuccessHandler(new RestLogoutSuccessHandler())
        .and().exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
        .accessDeniedHandler(new RestAccessDeniedHandler());
    }
    
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
