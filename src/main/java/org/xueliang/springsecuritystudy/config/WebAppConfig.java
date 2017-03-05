package org.xueliang.springsecuritystudy.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.xueliang.springsecuritystudy")
@PropertySource({"classpath:config.properties"})
public class WebAppConfig extends WebMvcConfigurerAdapter {
    
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(@Autowired MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter, @Autowired ContentNegotiationManager mvcContentNegotiationManager) {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        requestMappingHandlerAdapter.setMessageConverters(Collections.singletonList(mappingJackson2HttpMessageConverter));
        requestMappingHandlerAdapter.setContentNegotiationManager(mvcContentNegotiationManager);
        return requestMappingHandlerAdapter;
    }
    
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
    
    /**
     * 设置欢迎页
     * 相当于web.xml中的 welcome-file-list > welcome-file
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index.html");
    }
}