package org.xueliang.springsecuritystudy.config;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.xueliang.springsecuritystudy")
@PropertySource({"classpath:config.properties"})
public class WebAppConfig extends WebMvcConfigurerAdapter {
    
    /**
     * 内容协商
     * @return
     */
    @Bean
    public ContentNegotiationManager mvcContentNegotiationManager() {
        ContentNegotiationManagerFactoryBean contentNegotiationManagerFactoryBean = new ContentNegotiationManagerFactoryBean();
        contentNegotiationManagerFactoryBean.setFavorParameter(true);
        contentNegotiationManagerFactoryBean.setIgnoreAcceptHeader(true);
        contentNegotiationManagerFactoryBean.setDefaultContentType(MediaType.APPLICATION_JSON_UTF8);
        Properties mediaTypesProperties = new Properties();
        mediaTypesProperties.setProperty("json", MediaType.APPLICATION_JSON_UTF8_VALUE);
        contentNegotiationManagerFactoryBean.setMediaTypes(mediaTypesProperties);
        contentNegotiationManagerFactoryBean.afterPropertiesSet();
        return contentNegotiationManagerFactoryBean.getObject();
    }
    
    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(@Autowired ContentNegotiationManager mvcContentNegotiationManager) {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        contentNegotiatingViewResolver.setOrder(1);
        contentNegotiatingViewResolver.setContentNegotiationManager(mvcContentNegotiationManager);
        return contentNegotiatingViewResolver;
    }
    
    /**
     * 采用UTF-8编码，防止中文乱码
     * @return
     */
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
    
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(@Autowired StringHttpMessageConverter stringHttpMessageConverter, @Autowired ContentNegotiationManager mvcContentNegotiationManager) {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        requestMappingHandlerAdapter.setMessageConverters(Collections.singletonList(stringHttpMessageConverter));
        requestMappingHandlerAdapter.setContentNegotiationManager(mvcContentNegotiationManager);
        return requestMappingHandlerAdapter;
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