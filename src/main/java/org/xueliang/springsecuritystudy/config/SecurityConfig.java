package org.xueliang.springsecuritystudy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.xueliang.springsecuritystudy.security.CsrfSecurityRequestMatcher;
import org.xueliang.springsecuritystudy.security.RestAccessDeniedHandler;
import org.xueliang.springsecuritystudy.security.RestAuthenticationEntryPoint;
import org.xueliang.springsecuritystudy.security.RestAuthenticationFailureHandler;
import org.xueliang.springsecuritystudy.security.RestAuthenticationSuccessHandler;
import org.xueliang.springsecuritystudy.security.RestLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${api.csrftoken}")
    private String csrfTokenApi = null;
    
    @Value("${api.login}")
    private String loginApi = null;
    
    @Value("${api.logout}")
    private String logoutApi = null;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .mvcMatchers(csrfTokenApi).permitAll()
        .antMatchers("/**").fullyAuthenticated()
        .and()
        .formLogin()
        .loginProcessingUrl(loginApi)
        .successHandler(new RestAuthenticationSuccessHandler())
        .failureHandler(new RestAuthenticationFailureHandler())
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher(logoutApi))
//        .logoutSuccessUrl("/index.html")
        .logoutSuccessHandler(new RestLogoutSuccessHandler())
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
        .accessDeniedHandler(new RestAccessDeniedHandler());
    }
    
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");
    }
    
    public CsrfSecurityRequestMatcher csrfSecurityRequestMatcher() {
        return new CsrfSecurityRequestMatcher();
    }
}
